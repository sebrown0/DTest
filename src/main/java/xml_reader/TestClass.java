/**
 * 
 */
package xml_reader;

/**
 * @author Steve Brown
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
	private String name;
	private TestPriority priority;
	
	public TestClass(String name, TestPriority priority) {
		this.name = name;
		this.priority = priority;
	}
	
	public String getName() {
		return name;
	}
	public TestPriority getPriority() {
		return priority;
	}
}
