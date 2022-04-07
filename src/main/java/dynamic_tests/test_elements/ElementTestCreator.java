/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.interfaces.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface ElementTestCreator {
	String getMessage();
	void executeTest(Optional<Control> cntrl);
}
