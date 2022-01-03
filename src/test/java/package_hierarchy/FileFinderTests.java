/**
 * 
 */
package package_hierarchy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import utils.FileFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class FileFinderTests {
	private static final String ROOT = ".\\src\\test\\java";

	@Test
	void find_filePath_withoutRoot() throws IOException {			
		assertEquals(
				"package_hierarchy\\FileFinderTests.java", 
				FileFinder.findPathWithoutRoot(ROOT, "FileFinderTests.java"));
	}
	@Test
	void find_filePath_withRoot() throws IOException {			
		assertEquals(
				ROOT + "\\package_hierarchy\\FileFinderTests.java", 
				FileFinder.findPathWithRoot(ROOT, "FileFinderTests.java"));
	}
	
}
