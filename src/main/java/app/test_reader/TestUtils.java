/**
 * 
 */
package app.test_reader;

import java.nio.file.Path;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;

import providers.Tests;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class TestUtils {

	public static String getTestNameFromPath(Path p) {
		String result="";
		try {
			String[] fileNameParts = p.getFileName().toString().split(Pattern.quote("."));	
			result = fileNameParts[0];
		} catch (Exception e) {
			LogManager.getLogger().error("Error getting test name");
		}
		return result;
	}
	

	public static String getTestClassesPackage(Path p) {
		String testPackageName = "";
		for(int idx = p.getNameCount() - 2; idx > 0; idx--) {
			String part = p.getName(idx).toString();
			if(part.equalsIgnoreCase(Tests.TEST_PACKAGE)) {
				break;
			}
			testPackageName = addPartToPath(testPackageName, part);
		}
		return testPackageName;
	}
	private static String addPartToPath(String currPath, String part) {
		if(currPath != null && currPath.length() > 0) {
			currPath = part + "." + currPath;
		}else {
			currPath = part;
		}
		return currPath;
	}
	
}
