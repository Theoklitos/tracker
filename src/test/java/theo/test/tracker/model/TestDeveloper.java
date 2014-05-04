package theo.test.tracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import theo.test.tracker.model.Developer;

/**
 * Tests for the {@link Developer} bean
 * 
 * @author theoklitos
 * 
 */
public final class TestDeveloper {

	@Test
	public void nameIsSetProperly() {
		final Developer dev = new Developer();
		assertNull("", dev.getName());
		final String name = "theo";
		dev.setName(name);
		assertEquals(name, dev.getName());
	}
}
