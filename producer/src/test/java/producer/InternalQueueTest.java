package producer;

import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.Test;

import server.InternalQueueImplementation;

public class InternalQueueTest {

	@Test
	public void isSingletonObject() {

		assertTrue("All objects are singleton (have same hashcode)",
				InternalQueueImplementation.getSingletonQueueObject(Paths.get("."))
						.hashCode() == InternalQueueImplementation.getSingletonQueueObject(Paths.get(".")).hashCode());

	}

}
