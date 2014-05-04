package theo.test.tracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import theo.test.tracker.model.Week;
import theo.test.tracker.service.TrackerService;

/**
 * tests for the {@link DeveloperController}
 * 
 * @author theoklitos
 * 
 */
public final class TestPlanningController {

	private Mockery mockery;
	private TrackerService trackerService;
	private PlanningController controller;
	private Model model;

	@Before
	public void setup() {
		mockery = new Mockery();
		trackerService = mockery.mock(TrackerService.class);
		model = mockery.mock(Model.class);
		controller = new PlanningController();
		controller.setTrackerService(trackerService);
	}

	@Test
	public void testGeneratePlanning() {
		final List<Week> weeks = new ArrayList<Week>();
		mockery.checking(new Expectations() {
			{
				oneOf(model).addAttribute(with("prettyDateTime"), with(any(String.class)));
				oneOf(trackerService).generatePlanningWeeks();
				will(returnValue(weeks));
				oneOf(model).addAttribute("weeks", weeks);
				oneOf(trackerService).getLastAuditTrail();
				oneOf(model).addAttribute(with("auditTrailLines"), with(any(Object.class)));
			}
		});
		controller.generatePlanning(model);
	}
}
