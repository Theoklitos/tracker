package theo.test.tracker.dao;

import java.util.List;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;

/**
 * A data access object for all the data types in our web application.
 * 
 * @author theoklitos
 * 
 */
public interface TrackerDao {

	/**
	 * deletes the given developer, if one with such a name exists.
	 * 
	 * @return true if such a named developer was found + deleted, false otherwise
	 */
	boolean deleteDeveloper(String developerName);

	/**
	 * returns a list of all the developers
	 */
	List<Developer> getAllDevelopers();

	/**
	 * returns all the existing user stories
	 */
	List<Story> getAllStories();

	/**
	 * searches the developers stored and returns the one with the given name. Will return null if nothing is
	 * found.
	 */
	Developer getDeveloperByName(final String developerName);

	/**
	 * returns the story that has the given ID, or null if such a story does not exist
	 */
	Story getStoryById(long id);

	/**
	 * stores (or updates, if exists) the given {@link Developer}
	 */
	void saveDeveloper(Developer developer);

	/**
	 * stores (or updates, if exists) the given {@link Story}
	 */
	void saveStory(Story story);

}
