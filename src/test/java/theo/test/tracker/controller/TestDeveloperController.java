package theo.test.tracker.controller;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import theo.test.tracker.model.Developer;
import theo.test.tracker.service.TrackerService;

/**
 * tests for the {@link DeveloperController}
 * 
 * @author theoklitos
 * 
 */
public final class TestDeveloperController {

	private Mockery mockery;
	private TrackerService trackerService;
	private DeveloperController controller;
	private Model model;

	private void expectShowAllDevelopers() {
		final ArrayList<Developer> devs = new ArrayList<Developer>();
		mockery.checking(new Expectations() {
			{
				oneOf(trackerService).getAllDevelopers();
				will(returnValue(devs));
				oneOf(model).addAttribute("developers", devs);
			}
		});
	}

	@Before
	public void setup() {
		mockery = new Mockery();
		trackerService = mockery.mock(TrackerService.class);
		model = mockery.mock(Model.class);
		controller = new DeveloperController();
		controller.setTrackerService(trackerService);
	}

	@Test
	public void showAllDevelopers() {
		expectShowAllDevelopers();
		controller.showDevs(model);
	}

	@Test
	public void testAddDeveloper() {
		mockery.checking(new Expectations() {
			{
				// normally we should either create a Matcher OR a factory, those ways are more testable
				oneOf(trackerService).saveDeveloper(with(any(Developer.class)));
			}
		});
		expectShowAllDevelopers();
		controller.addDeveloper(DeveloperController.CREATE_DEVELOPER_ACTION, "devName", model);

	}

	@Test
	public void testDeleteDeveloper() {
		final String developerName = "devName";
		mockery.checking(new Expectations() {
			{
				oneOf(trackerService).deleteDeveloper(developerName);
				will(returnValue(true));
			}
		});
		expectShowAllDevelopers();
		controller.addDeveloper(DeveloperController.DELETE_DEVELOPER_ACTION, developerName, model);
	}

	@Test
	public void testDeleteDeveloperNotFound() {
		final String developerName = "devName";
		mockery.checking(new Expectations() {
			{
				oneOf(trackerService).deleteDeveloper(developerName);
				will(returnValue(false));
				oneOf(model).addAttribute(with("errorMessage"), with(any(String.class)));
			}
		});
		expectShowAllDevelopers();
		controller.addDeveloper(DeveloperController.DELETE_DEVELOPER_ACTION, developerName, model);
	}
}
