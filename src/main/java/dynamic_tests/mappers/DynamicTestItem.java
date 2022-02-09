/**
 * 
 */
package dynamic_tests.mappers;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.elements.TestElement;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add all the included tests to the item's 
 * list of tests.
 */
public class DynamicTestItem {
	private IncludedElements includedElements;
	private List<Element> elements;
	private MenuItem item;
	private CoreData coreData;
	private List<DynamicContainer> menuItemTests;
	private HomePage hp;
	
	/** 
	 * @param includedElements: all elements that are included in the test.
	 * @param item: menu item from xml.
	 * @param menuItemTests: List of tests for the item. 
	 * @param hp: Home page.
	 */
	public DynamicTestItem(
			IncludedElements includedElements, MenuItem item,	
			List<DynamicContainer> menuItemContainers, HomePage hp) {
		
		this.includedElements = includedElements;
		this.menuItemTests = menuItemContainers;
		this.hp = hp;
		this.coreData = hp;
		this.item = item;
		this.elements = item.getElements();
	}

	public void addTests() {
		if(elements != null) {			
			elements.forEach(e -> {							
				if(includedElements.isIncluded(e.getElementType())) {
					addTestToItemContainer(
							DynamicTestFactory.getTest(e, hp, coreData, item)
					);
				}								
			});	
		}		
	}
			
	private void addTestToItemContainer(Optional<TestElement> test) {
		test.ifPresent(t -> {	
			menuItemTests.add(DynamicContainer.dynamicContainer(getKey(t), t.createTests())); 
		});
	}
	
	private String getKey(TestElement e) {
		return e.getType() + "." + e.getName();
	}
	
}
