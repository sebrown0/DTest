/**
 * 
 */
package dynamic_tests.elements.test_elements;

import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestFactory {

	/**
	 * Get the test for the element.
	 */
	public void addElementSpecificTestsTo(ElementTest test, ElementCreation el) {		
		String elType = el.getElementType();				

		switch (elType) {
			case "Button" -> {
				test.addTests(new ElementTestButton(el));
			}
			case "TextSelect", "TextOut" -> {
				test.addTests(new ElementTestTextOut(el));						
			}
			default -> { 
				test = null;
				throw new IllegalArgumentException("Unexpected value: " + elType); 
			}
		}  		
	}	
		
}
