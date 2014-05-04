package theo.test.tracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import theo.test.tracker.utils.TrackerUtils;

/**
 * In most agile methodologies, a user story briefly describes a requirement and how long it will take to
 * fullfil that requirement.
 * 
 * @author theoklitos
 * 
 */
@Entity
public final class Story implements Issue {

	/**
	 * describes the state that this story is in
	 * 
	 * @author theoklitos
	 * 
	 */
	public enum StoryStatus {
		/**
		 * brand new, no estimation has been done
		 */
		NEW,
		/**
		 * an estimation value has been given
		 */
		ESTIMATED,

		/**
		 * story is "done"
		 */
		COMPLETED
	}

	// this is not stored on its own, its fields are derived
	@Transient
	private final IssueInformation issueInformation;

	private int points;

	@Enumerated(EnumType.STRING)
	@Column(name = "story_status")
	private StoryStatus storyStatus;

	public Story() {
		issueInformation = new IssueInformation();
		storyStatus = StoryStatus.NEW;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	public Date getCreationDate() {
		return issueInformation.getCreationDate();
	}

	@Column(name = "description")
	public String getDescription() {
		return issueInformation.getDescription();
	}

	// owner side
	@ManyToOne
	@JoinColumn(name = "developer")
	public Developer getDeveloper() {
		return issueInformation.getDeveloper();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return issueInformation.getId();
	}

	/**
	 * returns the estimated point value of this story
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * returns a pretty readable form of the creation date
	 */
	@Transient
	public String getPrettyCreationDate() {
		return TrackerUtils.getPrettyDate(getCreationDate());
	}

	/**
	 * what is the state of this story? returns a {@link StoryStatus}
	 */
	public StoryStatus getStoryStatus() {
		return storyStatus;
	}

	@Column(name = "title")
	public String getTitle() {
		return issueInformation.getTitle();
	}

	public void setCreationDate(final Date creationDate) {
		issueInformation.setCreationDate(creationDate);
	}

	public void setDescription(final String description) {
		issueInformation.setDescription(description);
	}

	public void setDeveloper(final Developer developer) {
		issueInformation.setDeveloper(developer);
	}

	public void setId(final Long id) {
		issueInformation.setId(id);
	}

	/**
	 * sets the estimated point value of this story
	 */
	public void setPoints(final int points) {
		if (points < 0) {
			throw new IllegalArgumentException("A story cannot have negative points!");
		}
		this.points = points;
		if (getStoryStatus().equals(StoryStatus.NEW)) {
			setStoryStatus(StoryStatus.ESTIMATED);
		}
	}

	/**
	 * sets the state of this story
	 */
	public void setStoryStatus(final StoryStatus storyStatus) {
		this.storyStatus = storyStatus;
		if (storyStatus.equals(StoryStatus.NEW)) {
			points = 0;
		}
	}

	public void setTitle(final String title) {
		issueInformation.setTitle(title);
	}

	@Override
	public String toString() {
		String pointText = "";
		if (points != 0) {
			if (points == 1) {
				pointText = ", 1 story point";
			} else {
				pointText = ", " + points + " story points";
			}
		}
		return issueInformation.toString() + ". Story status: " + storyStatus + pointText;
	}

}
