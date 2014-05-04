package theo.test.tracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A planning week consists of a series of tasks and a maximum point limit that they must fit inside
 * 
 * @author theoklitos
 * 
 */
public final class Week {

	private final List<Story> stories;
	private final int maxPoints;

	public Week(final int maxPoints) {
		if (maxPoints < 1) {
			throw new IllegalArgumentException("Max points must be at least =1");
		}
		this.maxPoints = maxPoints;
		stories = new ArrayList<Story>();
	}

	/**
	 * adds all the stories in the list to this week
	 * 
	 * @throws IllegalArgumentException
	 *             if the story points have exceeded the max
	 */
	public void addAllStories(final List<Story> stories) {
		for (final Story story : stories) {
			addStory(story);
		}
	}

	/**
	 * Adds the story to this week.
	 * 
	 * @throws IllegalArgumentException
	 *             if the story points have exceeded the max
	 */
	public void addStory(final Story story) {
		final int futurePoints = getTotalPoints() + story.getPoints();
		if (futurePoints > maxPoints) {
			throw new IllegalArgumentException("Maximum week points exceeded! " + futurePoints
				+ " > " + maxPoints);
		} else {
			stories.add(story);
		}
	}

	/**
	 * returns the max story point capacity for this week
	 */
	public int getMaxPoints() {
		return maxPoints;
	}

	/**
	 * returns the stories that have been assigned to this week
	 */
	public List<Story> getStories() {
		return stories;
	}

	/**
	 * adds up all the points in the individual stories
	 */
	public int getTotalPoints() {
		int result = 0;
		for (final Story story : stories) {
			result += story.getPoints();
		}
		return result;
	}

	@Override
	public String toString() {
		final StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(getTotalPoints() + " out of " + maxPoints + " story points.");
		for (final Story story : stories) {
			stringBuffer
					.append("\nStory #" + story.getId() + ": " + story.getPoints() + " points.");
		}
		return stringBuffer.toString();
	}
}
