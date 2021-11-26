/**
 * 
 */
package app.test_reader;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public interface TestPackageContents {
	String getPackageName();
	String getApplicable();
	List<TestClass> getTestClasses();	
}
