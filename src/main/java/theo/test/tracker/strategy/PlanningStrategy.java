package theo.test.tracker.strategy;

import java.util.List;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Week;

/**
 * Contains logic that dictates how many a {@link Story} fit into a {@link Week}
 * 
 * @author theoklitos
 * 
 */
public interface PlanningStrategy {

	static final int MAX_STORY_POINTS_PER_DEVELOPER_PER_WEEK = 10;

	/**
	 * The point of this whole webapp! Based on the # of developer and the point estimation of the stories,
	 * returns a number of {@link Week}s that have user stories assigned to them
	 */
	List<Week> generatePlanningWeeks(List<Developer> allDevelopers, List<Story> allStories);

	/**
	 * Returns the generation logic audit trail for the most recently generated project plan
	 */
	String getLastAuditTrail();

}
