/**
 * 
 */
package xml_reader;

import java.util.List;

/**
 * @author Steve Brown
 *
 */
public interface TestPackageContents {
	String getPackageName();
	String getApplicable();
	List<TestClass> getTestClasses();	
}
