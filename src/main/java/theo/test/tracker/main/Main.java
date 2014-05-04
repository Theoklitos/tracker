package theo.test.tracker.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import theo.test.tracker.controller.DeveloperController;

/**
 * A class containing a main method, for quick testing
 * 
 * @author theoklitos
 * 
 */
public final class Main {

	private final static Log LOG = LogFactory.getLog(Main.class);

	public static final void main(final String args[]) {
		final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		final DeveloperController control = ctx.getBean(DeveloperController.class);

		LOG.info(control);

		ctx.close();
	}
}
