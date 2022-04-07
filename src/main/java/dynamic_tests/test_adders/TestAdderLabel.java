/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import dynamic_tests.test_elements.TestElementDetails;
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
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
		testFactory.createTextCheck(details, el.getText());
	}
}
