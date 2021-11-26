/**
 * 
 */
package app.test_reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;

import providers.Tests;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Get all tests in IncludedTestsReader.TEST_DIR.
 */
public class AllTests implements TestFileReader {

	@Override
	public void addTestsToPackage(TestPackage testPackage) {
		try (Stream<Path> paths = Files.walk(Paths.get(Tests.TEST_DIR))) {

			List<TestClass>	testClasses =	paths
				.filter(Files::isRegularFile)
				.filter(f -> f.toString().contains("Test"))
				.map(t -> {  return createTestClass(t); })
				.collect(Collectors.toList());
		
			testPackage.setTestClasses(testClasses);
			
		} catch (IOException e) {
			LogManager.getLogger().error("Error getting all classes in test dir");			
		}		
	}

	private TestClass createTestClass(Path p) {		
		String fileName = TestUtils.getTestName(p);		
		String testPackageName = TestUtils.getTestClassesPackage(p);			
		return new TestClass(fileName, testPackageName);
	}	

}
