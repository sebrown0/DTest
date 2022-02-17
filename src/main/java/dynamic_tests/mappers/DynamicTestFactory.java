/**
 * 
 */
package dynamic_tests.mappers;

import java.util.Optional;

import dynamic_tests.elements.ElementTestButton;
import dynamic_tests.elements.TestElement;
import object_models.pages.homepage.CoreData;
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
	/**
	 * @param el: The Element from XML.
	 * @param hp: Home page.
	 * @param coreData: app data
	 * @param item: menu item from xml, i.e. employee details.
	 * 
	 * Get the test for the element.
	 */
	public static Optional<TestElement> getTest(
			ElementCreation el, HomePage hp, CoreData coreData, MenuItem item) {
		
		String elementType = el.getElementType();
		Optional<TestElement> test = null;
		 	
		switch (elementType) {
			case "button" -> {
				test = Optional.of(
						new ElementTestButton(hp, coreData, item, el));				
			}
			default -> { 
				throw new IllegalArgumentException("Unexpected value: " + elementType); 
			}
		}	
		return test;  		
	}	
}