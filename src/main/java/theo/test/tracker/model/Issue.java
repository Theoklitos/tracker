package theo.test.tracker.model;

import java.util.Date;

/**
 * Common interface for {@link Bug}s and {@link Story}s: An {@link Issue} is "something" that a developer
 * needs to implement and track.
 * 
 * @author theoklitos
 * 
 */
public interface Issue {

	/**
	 * when was this issue created?
	 */
	public Date getCreationDate();

	/**
	 * Returns a description for this issue
	 */
	public String getDescription();

	/**
	 * Which {@link Developer} is working on this issue? Returns null if no one is.
	 */
	public Developer getDeveloper();

	/**
	 * Returns the title (effectively the calling name) of this issue.
	 */
	public String getTitle();

	/**
	 * sets the date that this issue was first created
	 */
	public void setCreationDate(final Date creationDate);

	/**
	 * Sets the description for this issue
	 */
	public void setDescription(final String description);

	/**
	 * Sets the {@link Developer} that is working on this issue
	 */
	public void setDeveloper(final Developer developer);

	/**
	 * Sets the title for this issue
	 */
	public void setTitle(final String title);
}
