package theo.test.tracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the week
 * 
 * @author theoklitos
 * 
 */
public final class TestWeek {

	@Test(expected = IllegalArgumentException.class)
	public void pointLimitExceeded() {
		final Week week = new Week(10);
		final Story story = new Story();
		story.setPoints(11);
		week.addStory(story);
	}

	@Test
	public void testTotalPoints() {
		final Week week = new Week(100);
		final Story story1 = new Story();
		story1.setPoints(20);
		final Story story2 = new Story();
		story2.setPoints(4);
		week.addStory(story1);
		assertEquals(20, week.getTotalPoints());
		week.addStory(story2);
		assertEquals(24, week.getTotalPoints());
	}
}
