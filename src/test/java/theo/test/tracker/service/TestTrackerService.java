package theo.test.tracker.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import theo.test.tracker.dao.TrackerDao;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Story.StoryStatus;

/**
 * Tests for the {@link TrackerService}
 * 
 * @author theoklitos
 * 
 */
public final class TestTrackerService {

	private TrackerServiceImpl trackerService;
	private Mockery mockery;
	private TrackerDao trackerDao;

	/**
	 * sets the expectation that the stories that will be fetched from the DAO are the ones given as
	 * parameters
	 */
	private void expectGetAllStories(final Story... stories) {
		final List<Story> allStories = new ArrayList<Story>();
		for (final Story story : stories) {
			allStories.add(story);
		}
		mockery.checking(new Expectations() {
			{
				oneOf(trackerDao).getAllStories();
				will(returnValue(allStories));
			}
		});
	}

	@Before
	public void setup() {
		mockery = new Mockery();
		trackerService = new TrackerServiceImpl();
		trackerDao = mockery.mock(TrackerDao.class);
		trackerService.setTrackerDao(trackerDao);
	}

	@Test
	public void testGetAllStoriesOrderedByDate() {
		final Story story1 = new Story();
		story1.setTitle("story1");
		story1.setCreationDate(new Date(10));
		final Story story2 = new Story();
		story2.setTitle("story2");
		story2.setCreationDate(new Date(1000));
		final Story story3 = new Story();
		story3.setTitle("story3");
		story3.setCreationDate(new Date(100000));
		// we initialy return them in a unordered way
		expectGetAllStories(story2, story3, story1);
		final List<Story> sortedStories = trackerService.getAllStoriesOrderedByDate();
		assertEquals(story1, sortedStories.get(0));
		assertEquals(story2, sortedStories.get(1));
		assertEquals(story3, sortedStories.get(2));
	}

	@Test
	public void testGetOpenStories() {
		final Story closedStory = new Story();
		closedStory.setStoryStatus(StoryStatus.COMPLETED);
		expectGetAllStories(closedStory);
		assertEquals(0, trackerService.getOpenStoriesSortedByDate().size());
		final Story openStory = new Story();
		openStory.setStoryStatus(StoryStatus.ESTIMATED);
		expectGetAllStories(closedStory, openStory);
		final List<Story> openStories = trackerService.getOpenStoriesSortedByDate();
		assertEquals(1, openStories.size());
		assertEquals(openStory, openStories.get(0));
	}

}
