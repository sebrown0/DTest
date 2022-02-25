/**
 * 
 */
package dynamic_tests.mappers;

import java.util.Optional;

import dynamic_tests.elements.ElementTest;
import dynamic_tests.elements.ElementTestButton;
import dynamic_tests.elements.ElementTestFactory;
import dynamic_tests.elements.ElementTestTextOut;
import dynamic_tests.elements.TestElement;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestFactory {
	private HomePage hp;
	private MenuItem item;
	
	/**
	 * @param hp: Home page.
	 * @param item: menu item from xml, i.e. employee details.
	 */
	public DynamicTestFactory(HomePage hp, MenuItem item) {
		this.hp = hp;
		this.item = item;
	}

	/**
	 * @param testNode: The test node.
	 * @param el: The Element from XML.	  
	 * 
	 * Get the test for the element.
	 */
	public Optional<TestElement> getTest(
		TestNode testNode, ElementCreation el) {
		
		String elType = el.getElementType();		
//		Optional<TestElement> test = null;
		
		ElementTest test = new ElementTest(testNode, hp, item, elType, el);
		 	
		switch (elType) {
			case "Button" -> {
				test.addTests(new ElementTestButton(el));
//				test = Optional.of(
//						new ElementTestButton(testNode, hp, item, el));				
			}
			case "TextSelect" -> {
				test.addTests(new ElementTestTextOut(el));
//				test = Optional.of(
//						new ElementTestTextOut(testNode, hp, item, el));						
			}
			default -> { 
				throw new IllegalArgumentException("Unexpected value: " + elType); 
			}
		}	
		return Optional.ofNullable(test);  		
	}	
	
//	private String getTestData(ElementDetails elDetails) {
//		ElementTestData data = elDetails.getTestData();
//		return (data != null) ? data.getData()  : "";
//	}
}
