package theo.test.tracker.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import theo.test.tracker.model.Story;
import theo.test.tracker.model.Story.StoryStatus;

/**
 * misc helper functions
 * 
 * @author theoklitos
 * 
 */
public final class TrackerUtils {

	private static final String PRETTY_DATETIME_FORMAT = "yyyy MMMM dd, HH:mm";

	/**
	 * From the given stories, returns only the ones that are not COMPLETED
	 */
	public static final List<Story> getOpenStories(final List<Story> stories) {
		final List<Story> result = new ArrayList<Story>();
		for (final Story story : stories) {
			if (!story.getStoryStatus().equals(StoryStatus.COMPLETED)) {
				result.add(story);
			}
		}
		return result;
	}

	/**
	 * returns the given {@link Date} as a pretty readable String
	 */
	public static String getPrettyDate(final Date date) {
		final DateTime dt = new DateTime(date);
		final DateTimeFormatter formatter = DateTimeFormat.forPattern(PRETTY_DATETIME_FORMAT);
		return formatter.print(dt);
	}
}
