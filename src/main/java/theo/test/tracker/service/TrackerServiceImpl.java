package theo.test.tracker.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import theo.test.tracker.dao.TrackerDao;
import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Week;
import theo.test.tracker.strategy.PlanningStrategy;
import theo.test.tracker.utils.TrackerUtils;

/**
 * Implementation of the {@link TrackerService}
 * 
 * @author theoklitos
 * 
 */
@Service
public final class TrackerServiceImpl implements TrackerService {

	private TrackerDao trackerDao;
	private PlanningStrategy strategy;

	public boolean deleteDeveloper(final String developerName) {
		return trackerDao.deleteDeveloper(developerName);
	}

	public List<Week> generatePlanningWeeks() {
		return strategy.generatePlanningWeeks(getAllDevelopers(), getAllStories());
	}

	public List<Developer> getAllDevelopers() {
		return trackerDao.getAllDevelopers();
	}

	public List<Story> getAllStories() {
		return trackerDao.getAllStories();
	}

	public List<Story> getAllStoriesOrderedByDate() {
		final List<Story> allStories = getAllStories();
		Collections.sort(allStories, new Comparator<Story>() {
			public int compare(final Story s1, final Story s2) {
				return s1.getCreationDate().compareTo(s2.getCreationDate());
			}
		});
		return allStories;
	}

	public Developer getDeveloperByName(final String developerName) {
		return trackerDao.getDeveloperByName(developerName);
	}

	public String getLastAuditTrail() {
		if (strategy == null) {
			return "";
		} else {
			return strategy.getLastAuditTrail();
		}
	}

	public List<Story> getOpenStoriesSortedByDate() {
		return TrackerUtils.getOpenStories(getAllStoriesOrderedByDate());
	}

	public Story getStoryById(final Integer id) {
		return trackerDao.getStoryById(id);
	}

	public void saveDeveloper(final Developer developer) {
		trackerDao.saveDeveloper(developer);
	}

	public void saveStory(final Story story) {
		trackerDao.saveStory(story);
	}

	// this is autowired for this prototype app, but in a live setting could be a Strategy pattern that swaps
	// algorithms in run time
	@Autowired
	public void setPlanningStrategy(final PlanningStrategy strategy) {
		this.strategy = strategy;
	}

	@Autowired
	public void setTrackerDao(final TrackerDao trackerDao) {
		this.trackerDao = trackerDao;
	}
}
