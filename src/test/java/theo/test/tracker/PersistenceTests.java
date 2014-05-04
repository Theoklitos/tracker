package theo.test.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;

/**
 * Tests the saving/loading of objects. Uses a test (H2) database. This is more of an integration test, since
 * an actual DB is used.
 * 
 * @author theoklitos
 * 
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public final void createAndDeleteDeveloper() {
		final Session session = sessionFactory.getCurrentSession();
		final Developer developer = new Developer();
		developer.setName("theo");
		session.save(developer);
		session.flush();
		Developer retrieved = (Developer) session.get(Developer.class, developer.getId());
		assertEquals(developer, retrieved);
		session.delete(developer);
		session.flush();
		retrieved = (Developer) session.get(Developer.class, developer.getId());
		assertNull(retrieved);
	}

	@Test
	@Transactional
	public final void createStoryWithDeveloper() {
		final Session session = sessionFactory.getCurrentSession();
		final Developer developer = new Developer();
		developer.setName("theo");
		session.save(developer);
		session.flush();

		final Story userStory = new Story();
		userStory.setTitle("Test story");
		userStory.setDeveloper(developer);
		session.save(userStory);
		session.flush();

		final Story retrieved = (Story) session.get(Story.class, userStory.getId());
		assertEquals(userStory, retrieved);
		assertEquals("Test story", retrieved.getTitle());
		assertEquals(developer, retrieved.getDeveloper());
		System.out.println("\n" + userStory);
	}
}
