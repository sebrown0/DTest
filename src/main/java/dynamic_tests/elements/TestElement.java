/**
 * 
 */
package dynamic_tests.elements;

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
 */
public interface TestElement {
	String getName();
	String getType();
	List<DynamicTest> createTests();
}
