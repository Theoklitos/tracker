package theo.test.tracker.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import theo.test.tracker.model.Developer;
import theo.test.tracker.model.Story;

/**
 * The dao implemention using hibernate's {@link SessionFactory}.
 * 
 * @author theoklitos
 * 
 */
@Repository
@Transactional
public final class TrackerDaoHibernate implements TrackerDao {

	private final static Log LOG = LogFactory.getLog(TrackerDaoHibernate.class);

	private SessionFactory sessionFactory;

	public boolean deleteDeveloper(final String developerName) {
		final Developer developer = getDeveloperByName(developerName);
		if (developer != null) {
			sessionFactory.getCurrentSession().delete(developer);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Developer> getAllDevelopers() {
		return sessionFactory.getCurrentSession().createQuery("from Developer c").list();
	}

	@SuppressWarnings("unchecked")
	public List<Story> getAllStories() {
		return sessionFactory.getCurrentSession().createQuery("from Story c").list();
	}

	public Developer getDeveloperByName(final String name) {
		for (final Developer deveveloper : getAllDevelopers()) {
			if (deveveloper.getName().equals(name)) {
				return deveveloper;
			}
		}
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Story getStoryById(final long id) {
		// this would be better with a named query on the story table
		return (Story) sessionFactory.getCurrentSession().get(Story.class, id);
	}

	public void saveDeveloper(final Developer developer) {
		sessionFactory.getCurrentSession().saveOrUpdate(developer);
		LOG.info("Developer \"" + developer.getName() + "\" stored.");
	}

	public void saveStory(final Story story) {
		sessionFactory.getCurrentSession().saveOrUpdate(story);
		LOG.info("Story #" + story.getId() + " stored.");
	}

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
