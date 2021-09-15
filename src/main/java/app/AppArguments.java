/**
 * 
 */
package app;

import xml_reader.test_file.TestPackage;

/**
 * @author Steve Brown
 *
 * Parsed arguments for the app.
 */
public class AppArguments {
	private String moduleName;
	private String testFileName;
	private TestPackage testPackage;
	
	public AppArguments(String moduleName, String testFileName, TestPackage testPackage) {
		this.moduleName = moduleName;
		this.testFileName = testFileName;
		this.testPackage = testPackage;
	}

	public String getModuleName() {
		return moduleName;
	}
	public String getTestFileName() {
		return testFileName;
	}

	public TestPackage getTestPackage() {
		return testPackage;
	}
	
}
