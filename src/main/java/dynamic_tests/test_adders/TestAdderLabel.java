/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 */
public class TestAdderLabel implements TestAdder {
	private ElementCreation el;
	
	public TestAdderLabel(ElementCreation el) {
		this.el = el;
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory) {
		testFactory.createTextCheck(el.getText());
	}
}
