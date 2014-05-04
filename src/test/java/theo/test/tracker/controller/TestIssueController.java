package theo.test.tracker.controller;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;
import theo.test.tracker.service.TrackerService;

/**
 * tests for the main {@link IssueController}
 * 
 * @author theoklitos
 * 
 */
public final class TestIssueController {

	private Mockery mockery;
	private TrackerService trackerService;
	private IssueController controller;
	private Model model;

	@Test
	public void createStory() {
		final String developerName = "developerName";
		mockery.checking(new Expectations() {
			{
				oneOf(trackerService).getDeveloperByName(developerName);
				// we should create stories with a factory, or make a custom matcher. That way it would be
				// easier to test.
				oneOf(trackerService).saveStory(with(any(Story.class)));
			}
		});
		expectShowStories(false);
		controller.createOrEditStory(null, "title", "description", developerName, "NEW", "45",
				model);

	}

	private void expectShowStories(final boolean showOnlyOpen) {
		final ArrayList<Developer> allDevs = new ArrayList<Developer>();
		final ArrayList<Story> allStories = new ArrayList<Story>();

		mockery.checking(new Expectations() {
			{
				oneOf(trackerService).getAllDevelopers();
				will(returnValue(allDevs));
				oneOf(model).addAttribute("developers", allDevs);
				if (showOnlyOpen) {
					oneOf(trackerService).getOpenStoriesSortedByDate();
					will(returnValue(allStories));
				} else {
					oneOf(trackerService).getAllStoriesOrderedByDate();
					will(returnValue(allStories));
				}
				oneOf(model).addAttribute("stories", allStories);
			}
		});
	}

	@Before
	public void setup() {
		mockery = new Mockery();
		trackerService = mockery.mock(TrackerService.class);
		controller = new IssueController();
		controller.setTrackerService(trackerService);
		model = mockery.mock(Model.class);
	}

	@Test
	public void showAllStories() {
		expectShowStories(false);
		controller.showStories("all", model);
	}

	@Test
	public void showOpenStories() {
		expectShowStories(true);
		controller.showStories("true", model);
	}

}
