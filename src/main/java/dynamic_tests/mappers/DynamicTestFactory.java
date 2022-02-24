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
	private HomePage hp;
	private CoreData coreData;
	private MenuItem item;
	
	/**
	 * @param hp: Home page.
	 * @param coreData: app data
	 * @param item: menu item from xml, i.e. employee details.
	 */
	public DynamicTestFactory(HomePage hp, CoreData coreData, MenuItem item) {
		this.hp = hp;
		this.coreData = coreData;
		this.item = item;
	}

	/**
	 * @param el: The Element from XML.	 * 
	 * 
	 * Get the test for the element.
	 */
	public Optional<TestElement> getTest(TestNode testNode, ElementCreation el) {		
		String elementType = el.getElementType();
		Optional<TestElement> test = null;
		 	
		switch (elementType) {
			case "button" -> {
				test = Optional.of(
						new ElementTestButton(testNode, hp, coreData, item, el));				
			}
			default -> { 
				throw new IllegalArgumentException("Unexpected value: " + elementType); 
			}
		}	
		return test;  		
	}	
}
