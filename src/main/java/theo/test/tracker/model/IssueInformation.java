package theo.test.tracker.model;

import java.util.Date;

import theo.test.tracker.utils.TrackerUtils;

/**
 * common fields for the types {@link Story} and {@link Bug}, so that we won't use inheritance
 * 
 * @author theoklitos
 * 
 */
public final class IssueInformation implements Issue {

	protected static final String EMPTY_DESCRIPTION_TEXT = "No Description Given";

	private Long id;
	private String title;
	private String description;
	private Date creationDate;
	private Developer developer;

	public IssueInformation() {
		creationDate = new Date();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getDescription() {
		if (description == null || description.trim().equals("")) {
			return EMPTY_DESCRIPTION_TEXT;
		} else {
			return description;
		}
	}

	public Developer getDeveloper() {
		return developer;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setDeveloper(final Developer developer) {
		this.developer = developer;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		String assignedToText = "No-one";
		if (developer != null) {
			assignedToText = developer.getName();
		}
		return "#" + id + ": " + title + ". Description: " + getDescription() + ". Created at "
			+ TrackerUtils.getPrettyDate(creationDate) + ", assigned to: " + assignedToText;
	}

}
