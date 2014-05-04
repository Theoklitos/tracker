package theo.test.tracker.strategy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.model.Week;

/**
 * tests the one and only {@link PlanningStrategy}
 * 
 * @author theoklitos
 * 
 */
public final class TestSimplePlanningStrategy {

	private SimplePlanningStrategy strategy;
	private ArrayList<Story> stories;
	private ArrayList<Developer> developers;

	@Test
	public void correntExecution() {
		// this should have generated 3 weeks similar to the ones given in the TestTask.pdf
		final List<Week> result = strategy.generatePlanningWeeks(developers, stories);
		assertEquals(3, result.size());
		assertEquals(3, result.get(0).getStories().size());
		assertEquals(40, result.get(0).getTotalPoints());

		assertEquals(2, result.get(1).getStories().size());
		assertEquals(34, result.get(1).getTotalPoints());

		assertEquals(1, result.get(2).getStories().size());
		assertEquals(10, result.get(2).getTotalPoints());
	}

	/**
	 * helper function to set developers
	 */
	private void createAddNamedDevelopers(final String... names) {
		for (final String name : names) {
			final Developer developer = new Developer();
			developer.setName(name);
			developers.add(developer);
		}
	}

	/**
	 * helper function to generate stories
	 */
	private void createAddStoriesForPoints(final int... points) {
		int count = 0;
		for (final int point : points) {
			final Story story = new Story();
			story.setPoints(point);
			story.setId(Long.valueOf(count));
			count++;
			stories.add(story);
		}
	}

	@Before
	public void setup() {
		stories = new ArrayList<Story>();
		developers = new ArrayList<Developer>();
		strategy = new SimplePlanningStrategy();
		createAddStoriesForPoints(14, 20, 4, 14, 22, 10);
		createAddNamedDevelopers("Carmack", "Gates", "Romero", "Linus");
	}

	@Test
	public void zeroDevelopers() {
		assertEquals(0, strategy.generatePlanningWeeks(new ArrayList<Developer>(), stories).size());
	}
}
