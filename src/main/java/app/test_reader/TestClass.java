/**
 * 
 */
package app.test_reader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Represents a JUNIT test class found in 
 * an included tests XML file.
 * 
 * When the file is read each class node 
 * is converted to this.
 * 
 */
public class TestClass {
	public enum TestPriority { HIGH, MED, LOW };
	
	// The specific path to the class from the test dir.
	private String packageName; 
	private String className;
	private TestPriority priority;
	
	public TestClass(String className, String packageName) {
		this.className = className;
		this.packageName = packageName;
		this.priority = TestPriority.HIGH;
	}
	
	public TestClass(String className, String packageName, TestPriority priority) {
		this.className = className;
		this.packageName = packageName;
		this.priority = priority;
	}
	
	public String getName() {
		return className;
	}
	public TestPriority getPriority() {
		return priority;
	}
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "TestClass [myPackageName=" + packageName + ", name=" + className + ", priority=" + priority + "]";
	}
	
	
}
