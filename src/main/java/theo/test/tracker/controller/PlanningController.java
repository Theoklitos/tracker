package theo.test.tracker.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import theo.test.tracker.service.TrackerService;
import theo.test.tracker.utils.TrackerUtils;

/**
 * Handles the planning page
 * 
 * @author theoklitos
 * 
 */
@Controller
public final class PlanningController {

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(PlanningController.class);

	private TrackerService service;

	/**
	 * Planning page. Generates the planning weeks and sends them to the view
	 */
	@RequestMapping(value = "/planning", method = RequestMethod.GET)
	public String generatePlanning(final Model model) {
		model.addAttribute("prettyDateTime", TrackerUtils.getPrettyDate(new Date()));
		model.addAttribute("weeks", service.generatePlanningWeeks());
		final String[] auditTrailLineArray = service.getLastAuditTrail().split("stop|\\n");
		model.addAttribute("auditTrailLines", auditTrailLineArray);
		return "planning";
	}

	@Autowired
	public void setTrackerService(final TrackerService trackerService) {
		this.service = trackerService;
	}
}
