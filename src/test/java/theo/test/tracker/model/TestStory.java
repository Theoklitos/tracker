package theo.test.tracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import theo.test.tracker.model.Story.StoryStatus;

/**
 * Tests for the {@link Story} class
 * 
 * @author theoklitos
 * 
 */
public final class TestStory {

	@Test
	public void setEstimatedStatus() {
		final Story story = new Story();
		story.setPoints(10);
		assertEquals(StoryStatus.ESTIMATED, story.getStoryStatus());
		story.setStoryStatus(StoryStatus.NEW);
		assertEquals(0, story.getPoints());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNegativePoints() {
		final Story story = new Story();
		story.setPoints(-5);
	}

}
