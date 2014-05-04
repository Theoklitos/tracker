package theo.test.tracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import theo.test.tracker.model.Story;
import theo.test.tracker.model.Story.StoryStatus;
import theo.test.tracker.service.TrackerService;

/**
 * Controller that handles stories + bugs. Also handles what could be described as the main page.
 * 
 * @author theoklitos
 * 
 */
@Controller
public final class IssueController {

	private static final String OPEN_STORIES_PARAMETER_VALUE = "open";
	private static final String ALL_STORIES_PARAMETER_VALUE = "all";

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(IssueController.class);

	private TrackerService service;

	/**
	 * About page, just for the heck of it.
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(final Model model) {
		return "about";
	}

	/**
	 * Handles POSTs that create or edit an existing stories
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.POST)
	public String createOrEditStory(@RequestParam(value = "id") final String id,
			@RequestParam(value = "title") final String title,
			@RequestParam(value = "description") final String description,
			@RequestParam(value = "developer") final String developer,
			@RequestParam(value = "status") final String status,
			@RequestParam(value = "points") final String points, final Model model) {
		final Story story;
		if (!StringUtils.isNullOrEmpty(id)) {
			// try to get existing story
			try {
				story = service.getStoryById(Integer.valueOf(id));
				if (story == null) {
					throw new IllegalArgumentException();
				}
			} catch (final IllegalArgumentException e) {
				model.addAttribute("errorMessage", "Story with ID \"" + id + "\" does not exist!");
				return showStories("all", model);
			}
		} else {
			// if no ID was given then this should be a new story
			story = new Story();
		}
		// now set the properties
		if (!StringUtils.isNullOrEmpty(title)) {
			story.setTitle(title);
		}
		if (!StringUtils.isNullOrEmpty(description)) {
			story.setDescription(description);
		}
		story.setDeveloper(service.getDeveloperByName(developer));
		if (!StringUtils.isNullOrEmpty(status)) {
			story.setStoryStatus(StoryStatus.valueOf(status));
		}
		if (!StringUtils.isNullOrEmpty(points)) {
			story.setPoints(Integer.valueOf(points));
		}
		// update and refresh the view
		service.saveStory(story);
		return showStories(ALL_STORIES_PARAMETER_VALUE, model);
	}

	/**
	 * Main page, overview and menu
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(final Model model) {
		model.addAttribute("developerNo", service.getAllDevelopers().size());
		model.addAttribute("issueNo", service.getAllStories().size());
		model.addAttribute("openStoriesNo", service.getOpenStoriesSortedByDate().size());
		model.addAttribute("openBugsNo", "0"); // bugs are not implemented
		return "main";
	}

	@Autowired
	public void setTrackerService(final TrackerService trackerService) {
		this.service = trackerService;
	}

	/**
	 * Shows all the stories. The first query parameter determines if only the open ones should be shown
	 */
	@RequestMapping(value = "/stories", method = RequestMethod.GET)
	public String showStories(@RequestParam(value = "show", required = false) final String show,
			final Model model) {
		System.out.println("SHOW PARAMETER: " + show);
		model.addAttribute("developers", service.getAllDevelopers());
		List<Story> stories = new ArrayList<Story>();
		if (StringUtils.isNullOrEmpty(show)
			|| show.toLowerCase().equals(ALL_STORIES_PARAMETER_VALUE)) {
			stories = service.getAllStoriesOrderedByDate();
		} else if (show.toLowerCase().equals(OPEN_STORIES_PARAMETER_VALUE)) {
			stories = service.getOpenStoriesSortedByDate();
		}
		model.addAttribute("stories", stories);
		return "stories";
	}
}
