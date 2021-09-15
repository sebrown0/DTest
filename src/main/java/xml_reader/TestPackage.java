/**
 * 
 */
package xml_reader;

import java.util.List;

/**
 * @author Steve Brown
 *
 * Represents a test package that contains TestClass(es).
 */
public class TestPackage implements TestPackageContents{
	private String packageName;
	private String applicable;
	private List<TestClass> testClasses;
	
	@Override
	public String getPackageName() {
		return packageName;
	}
	@Override
	public List<TestClass> getTestClasses() {
		return testClasses;
	}
	@Override
	public String getApplicable() {
		return applicable;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}	
	public void setTestClasses(List<TestClass> testClasses) {
		this.testClasses = testClasses;
	}	
	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}	
}
