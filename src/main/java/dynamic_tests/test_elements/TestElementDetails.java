/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getName & getType.
 * @version 1.2
 * 	Remove or update createTests() & getTests().
 * @since 1.0
 * 
 * Methods required from ElementTest.
 * 
 */
public interface TestElementDetails {
	String getName();
	String getType();
	List<DynamicTest> getTestList();
}
