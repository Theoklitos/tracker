package theo.test.tracker.service;

import java.util.List;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Week;
import theo.test.tracker.strategy.PlanningStrategy;

/**
 * Some sort of one-for-all middleware that contains the logic between the controllers and the data access
 * layer
 * 
 * @author theoklitos
 * 
 */
public interface TrackerService {

	/**
	 * deletes the given developer, if one with such a name exists.
	 * 
	 * @return true if such a named developer was found + deleted, false otherwise
	 */
	boolean deleteDeveloper(String developerName);

	/**
	 * Calls the defined {@link PlanningStrategy} to generate a number of {@link Week}s
	 */
	public List<Week> generatePlanningWeeks();

	/**
	 * returns a list of all the developers
	 */
	public List<Developer> getAllDevelopers();

	/**
	 * returns a list of all the user stories
	 */
	public List<Story> getAllStories();

	/**
	 * retrieves all the existing stories and sorts them by date, oldest-first
	 */
	public List<Story> getAllStoriesOrderedByDate();

	/**
	 * searches the developers stored and returns the one with the given name. Will return null if nothing is
	 * found.
	 */
	public Developer getDeveloperByName(String developerName);

	/**
	 * Returns the audit trail of the last project plan generation, if any. Empty string if no project plan
	 * generation has taken place so far.
	 */
	String getLastAuditTrail();

	/**
	 * returns the stories that are not of the StoryStatus.COMPLETED type, also sorts them by date
	 * (olderst-first)
	 */
	public List<Story> getOpenStoriesSortedByDate();

	/**
	 * returns the story that has the given ID, or null if such a story does not exist
	 */
	public Story getStoryById(Integer valueOf);

	/**
	 * stores (or updates, if exists) the given {@link Developer}
	 */
	void saveDeveloper(Developer developer);

	/**
	 * stores (or updates, if exists) the given {@link Story}
	 */
	public void saveStory(Story story);
}
