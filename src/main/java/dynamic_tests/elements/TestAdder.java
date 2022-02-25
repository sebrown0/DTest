/**
 * 
 */
package dynamic_tests.elements;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add a dynamic test for element to ElementTest.
 */
public interface TestAdder {
	void addTestTo(
			List<DynamicTest> toTestList, 
			String forElementName, 
			Optional<Control> withControl);
}
