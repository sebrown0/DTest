/**
 * 
 */
package app.test_reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;

import providers.Tests;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Get all tests in specific package (from test XML).
 */
public class AllTestsInPackage implements TestFileReader {
	private String[] packageParts;
	private TestPackage testPackage;
	
	@Override
	public void addTestsToPackage(TestPackage testPackage) {
		this.testPackage = testPackage;
		try (Stream<Path> paths = Files.walk(Paths.get(getPackagePath(testPackage.getPackageName())))) {			
		
			List<TestClass>	testClasses =	paths
					.filter(Files::isRegularFile)
					.map(t -> {  return createTestClass(t); })
					.collect(Collectors.toList());
			
			testPackage.setTestClasses(testClasses);
			
		} catch (IOException e) {
			LogManager.getLogger().error("Error getting all tests in package");
		}
	}

	private String getPackagePath(String packageName) {
		String result = Tests.TEST_DIR;
		if(packageName != null) {
			packageParts = packageName.split(Pattern.quote("."));
			if(packageParts.length > 0) {
				for(int i=0; i < packageParts.length; i++) {
					result += "\\" + packageParts[i];
				}			 
			}	
		}		
		return result;
	}

	private TestClass createTestClass(Path p) {
		String fileName = TestUtils.getTestName(p);		
		String packageName = testPackage.getPackageName();		
		return new TestClass(fileName, packageName);
	}
	
}
