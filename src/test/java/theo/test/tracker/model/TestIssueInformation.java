package theo.test.tracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

/**
 * tests for the {@link IssueInformation}
 * 
 * @author theoklitos
 * 
 */
public final class TestIssueInformation {

	@Test
	public void getEmptyDescription() {
		final IssueInformation info = new IssueInformation();
		assertEquals(IssueInformation.EMPTY_DESCRIPTION_TEXT, info.getDescription());
		final String description = "new description";
		info.setDescription(description);
		assertEquals(description, info.getDescription());
	}

	@Test
	public void setAndGetFields() {
		final IssueInformation info = new IssueInformation();
		final long id = 1L;
		info.setId(id);
		final String description = "new description";
		info.setDescription(description);
		final Developer developer = new Developer();
		info.setDeveloper(developer);
		final String title = "title!";
		info.setTitle(title);
		final Date date = new Date(1000);
		info.setCreationDate(date);

		assertTrue(id == info.getId());
		assertEquals(description, info.getDescription());
		assertEquals(developer, info.getDeveloper());
		assertEquals(title, info.getTitle());
		assertEquals(0, date.compareTo(info.getCreationDate()));
	}
}
