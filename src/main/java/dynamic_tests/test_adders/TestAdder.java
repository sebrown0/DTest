/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import dynamic_tests.test_elements.TestElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add a dynamic test(s) for the element
 * to ElementTest, using the factory.
 */
public interface TestAdder {
	void addTestsWith(ElementTestFactory testFactory, TestElementDetails details);
}
