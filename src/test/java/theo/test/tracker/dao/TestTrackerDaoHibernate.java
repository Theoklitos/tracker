package theo.test.tracker.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;

/**
 * Tests for the {@link TrackerDaoHibernate}
 * 
 * @author theoklitos
 * 
 */
public final class TestTrackerDaoHibernate {

	private SessionFactory sessionFactory;
	private Query query;
	private Session session;
	private Mockery mockery;
	private TrackerDaoHibernate dao;

	@Test
	public void deleteDeveloperExists() {
		final Developer developer = new Developer();
		final String developerName = "theo";
		developer.setName(developerName);
		expectGetAllDevelopers(developer);
		mockery.checking(new Expectations() {
			{
				oneOf(session).delete(developer);
			}
		});
		assertTrue(dao.deleteDeveloper("theo"));
	}

	@Test
	public void deleteDeveloperNotExists() {
		expectGetAllDevelopers();
		assertFalse(dao.deleteDeveloper("theo"));
	}

	/**
	 * sets the expectation that the developers in the DB are the ones given as parameters
	 */
	private void expectGetAllDevelopers(final Developer... developers) {
		final List<Developer> allDevs = new ArrayList<Developer>();
		for (final Developer developer : developers) {
			allDevs.add(developer);
		}
		mockery.checking(new Expectations() {
			{
				oneOf(session).createQuery("from Developer c");
				will(returnValue(query));
				oneOf(query).list();
				will(returnValue(allDevs));
			}
		});
	}

	/**
	 * sets the expectation that the stories in the DB are the ones given as parameters
	 */
	private void expectGetAllStories(final Story... stories) {
		final List<Story> allStories = new ArrayList<Story>();
		for (final Story story : stories) {
			allStories.add(story);
		}
		mockery.checking(new Expectations() {
			{
				oneOf(session).createQuery("from Story c");
				will(returnValue(query));
				oneOf(query).list();
				will(returnValue(allStories));
			}
		});
	}

	@Test
	public void getAllDevelopers() {
		expectGetAllDevelopers();
		dao.getAllDevelopers();
	}

	@Test
	public void getAllStories() {
		expectGetAllStories();
		dao.getAllStories();
	}

	@Test
	public void getDeveloperByNameExists() {
		final Developer developer = new Developer();
		final String developerName = "theo";
		developer.setName(developerName);
		expectGetAllDevelopers(developer);
		assertEquals(developer, dao.getDeveloperByName(developerName));
	}

	@Test
	public void getDeveloperByNameNotExist() {
		expectGetAllDevelopers();
		assertNull(dao.getDeveloperByName("non existing name"));
	}

	@Test
	public void saveDeveloper() {
		final Developer developer = new Developer();
		mockery.checking(new Expectations() {
			{
				oneOf(session).saveOrUpdate(developer);
			}
		});
		dao.saveDeveloper(developer);
	}

	@Test
	public void saveStory() {
		final Story story = new Story();
		mockery.checking(new Expectations() {
			{
				oneOf(session).saveOrUpdate(story);
			}
		});
		dao.saveStory(story);
	}

	@Before
	public void setup() {
		mockery = new Mockery();
		sessionFactory = mockery.mock(SessionFactory.class);
		session = mockery.mock(Session.class);
		query = mockery.mock(Query.class);
		mockery.checking(new Expectations() {
			{
				allowing(sessionFactory).getCurrentSession();
				will(returnValue(session));
			}
		});
		dao = new TrackerDaoHibernate();
		dao.setSessionFactory(sessionFactory);
	}

	@Test
	public void testGetStoryById() {
		final Story story = new Story();
		final long id = 15L;
		mockery.checking(new Expectations() {
			{
				oneOf(session).get(Story.class, id);
				will(returnValue(story));
			}
		});
		assertEquals(story, dao.getStoryById(id));
	}
}
