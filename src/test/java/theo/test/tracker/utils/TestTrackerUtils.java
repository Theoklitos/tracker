package theo.test.tracker.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import theo.test.tracker.model.Story;
import theo.test.tracker.model.Story.StoryStatus;

/**
 * Tests for the {@link TrackerUtils}
 * 
 * @author theoklitos
 * 
 */
public final class TestTrackerUtils {

	@Test
	public void testGetOpenStories() {
		final ArrayList<Story> stories = new ArrayList<Story>();
		final Story closedStory = new Story();
		closedStory.setStoryStatus(StoryStatus.COMPLETED);
		stories.add(closedStory);
		assertEquals(0, TrackerUtils.getOpenStories(stories).size());

		final Story openStory = new Story();
		openStory.setStoryStatus(StoryStatus.ESTIMATED);
		stories.add(openStory);
		final List<Story> openStories = TrackerUtils.getOpenStories(stories);
		assertEquals(1, openStories.size());
		assertEquals(openStory, openStories.get(0));
	}

}
