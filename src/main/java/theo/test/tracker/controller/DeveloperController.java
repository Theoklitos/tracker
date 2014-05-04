package theo.test.tracker.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import theo.test.tracker.model.Developer;
import theo.test.tracker.service.TrackerService;

/**
 * Controller that handles anything {@link Developer}-related
 * 
 * @author theoklitos
 * 
 */
@Controller
public final class DeveloperController {

	protected static final String CREATE_DEVELOPER_ACTION = "Create";
	protected static final String DELETE_DEVELOPER_ACTION = "Delete";

	private final static Log LOG = LogFactory.getLog(DeveloperController.class);

	private TrackerService service;

	/**
	 * creates or deletes a developer
	 */
	@RequestMapping(value = "/developers", method = RequestMethod.POST)
	public String addDeveloper(@RequestParam(value = "action") final String action,
			@RequestParam(value = "name") final String name, final Model model) {
		if (action.equals(CREATE_DEVELOPER_ACTION)) {
			final Developer newDeveloper = new Developer();
			newDeveloper.setName(name);
			service.saveDeveloper(newDeveloper);
			LOG.info("Created developer \"" + name + "\"");
		} else if (action.equals(DELETE_DEVELOPER_ACTION)) {
			if (service.deleteDeveloper(name)) {
				LOG.info("Deleted developer \"" + name + "\"");
			} else {
				model.addAttribute("errorMessage", "No developer with name \"" + name + "\" found!");
			}
		}
		return showDevs(model);
	}

	@Autowired
	public void setTrackerService(final TrackerService trackerService) {
		this.service = trackerService;
	}

	/**
	 * show a list of all the developers, also the view contains add + delete button
	 */
	@RequestMapping(value = "/developers", method = RequestMethod.GET)
	public String showDevs(final Model model) {
		model.addAttribute("developers", service.getAllDevelopers());
		return "developers";
	}
}
