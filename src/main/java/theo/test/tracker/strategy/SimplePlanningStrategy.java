package theo.test.tracker.strategy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Week;
import theo.test.tracker.utils.TrackerUtils;

/**
 * Simple strategy that fits the closest-matching (point-wise) stories into a week, until the week is full, at
 * which point a new week is initialized. All this until as much (open) stories have been placed in weeks.
 * 
 * @author theoklitos
 * 
 */
@Component
public final class SimplePlanningStrategy implements PlanningStrategy {

	private final static Log LOG = LogFactory.getLog(SimplePlanningStrategy.class);

	private StringBuffer lastAudit;

	public SimplePlanningStrategy() {
		lastAudit = new StringBuffer();
	}

	/**
	 * creats a new {@link Week} based on the parameters and adds it to the list
	 */
	private void createAndAddWeek(final int maxPerWeek, final List<Story> storiesForCurrentWeek,
			final List<Week> weeks) {
		final Week newWeek = new Week(maxPerWeek);
		newWeek.addAllStories(storiesForCurrentWeek);
		weeks.add(newWeek);
		logAndAudit("New week created, " + newWeek.getTotalPoints() + "/" + newWeek.getMaxPoints()
			+ ".");
	}

	public List<Week> generatePlanningWeeks(final List<Developer> allDevelopers,
			final List<Story> allStories) {
		lastAudit = new StringBuffer();
		final List<Week> result = new ArrayList<Week>();
		final List<Story> openStories = TrackerUtils.getOpenStories(allStories);
		if (allDevelopers.size() == 0 || openStories.size() == 0) {
			logAndAudit("No developers or no open stories exist, cannot plan. Terminating.");
			return result;
		}
		final int maxPerWeek = allDevelopers.size() * MAX_STORY_POINTS_PER_DEVELOPER_PER_WEEK;
		int spentThisWeek = 0;
		final List<Story> storiesForCurrentWeek = new ArrayList<Story>();
		while (openStories.size() > 0) {
			final Story bestFit = getClosestPointFitStory(openStories, maxPerWeek - spentThisWeek);
			if (bestFit == null) {
				logAndAudit("No (other) fitting story found.");
				if (storiesForCurrentWeek.size() > 0) {
					// create a week
					createAndAddWeek(maxPerWeek, storiesForCurrentWeek, result);
					storiesForCurrentWeek.clear();
					spentThisWeek = 0;
				} else {
					// terminate, algorithm can't proceed anymore
					logAndAudit("Terminating planning algorithm.");
					break;
				}
			} else {
				logAndAudit("Found best fit: Story #" + bestFit.getId() + ", "
					+ bestFit.getPoints() + " points.");
				spentThisWeek += bestFit.getPoints();
				storiesForCurrentWeek.add(bestFit);
				openStories.remove(bestFit);
				if (openStories.size() == 0) {
					createAndAddWeek(maxPerWeek, storiesForCurrentWeek, result);
					logAndAudit("All open stories have been assigned to a week, terminating planning algorithm.");
				}
			}
		}
		logAndAudit("End. Generated a project plan that spans " + result.size() + " week(s).");
		if (openStories.size() > 0) {
			logAndAudit("Warning! " + openStories.size()
				+ " stories were not assigned to planning.");
		}
		return result;
	}

	/**
	 * returns the story with the point estimation closer to the given number. Will return null if nothing can
	 * fit.
	 */
	private Story getClosestPointFitStory(final List<Story> stories, final int number) {
		Story closest = null;
		for (final Story story : stories) {
			if (closest == null & story.getPoints() <= number) {
				closest = story;
			} else if (closest != null && story.getPoints() <= number
				&& story.getPoints() > closest.getPoints()) {
				closest = story;
			}
		}
		return closest;
	}

	public String getLastAuditTrail() {
		return lastAudit.toString();
	}

	/**
	 * logs and adds the given message to the audit trail
	 */
	private void logAndAudit(final String message) {
		LOG.info(message);
		lastAudit.append(message + "\n");
	}

}
