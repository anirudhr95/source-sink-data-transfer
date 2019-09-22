package producer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import server.DirectorySpliterator;

public class DirectorySpliteratorTest {

	@Test
	public void readsOnlyJson() throws IOException {


		assertEquals("Should only read the three JSON files from src/main/resources", 3, DirectorySpliterator
				.list(Paths.get(Paths.get(".").toAbsolutePath().normalize().toString(), "../../resources")).count());

	}

}