/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.elements.IncludedElements;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Menu;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestMenu {
	// Key = element type + "_" + elementName, i.e. [buttonSave].
	private Map<String, List<DynamicTest>> menuItemTests;;  
  // List of all test containers in the menu item.
  private List<DynamicContainer> menuContainers = new ArrayList<>();
  // List of individual test containers for each menu item. 
  private List<DynamicContainer> menuItemContainers = new ArrayList<>();
  
  private String menuName;
  
  public DynamicContainer getMenuContainers(
  		Menu menu, IncludedElements includedElements, HomePage hp, String moduleName) {
  	
		if(menu.getMenuItems() != null && includedElements != null) {  	
  		menuName = menu.getPackageName();
			menu.getMenuItems().forEach(item -> {				
				//get map of tests
				getTestsForMenuItem(includedElements, item, hp, moduleName);	
				//add the tests in the map to a list of DynamicContainer(s)
				addTestsToMenuItemContainer();
				//add the above list to the menu container
				addMenuItemContainerToMenuContainer(item);
			});	
		}else {
			LogManager
				.getLogger(DynamicTestMenu.class)
				.error(
						String.format(
								"Cannot get DynamicContainer for " +
								"DynamicTestMenu with Menu [%s] & IncludedElements[%s]", 
								menu.toString(), includedElements.toString()));
		}
		return DynamicContainer.dynamicContainer(menu.getName(), menuContainers);
	}
  private void getTestsForMenuItem(IncludedElements includedElements, MenuItem item, HomePage hp, String moduleName) {
  	item.setTestModuleName(moduleName);
  	item.setTestMenuName(menuName);
  	menuItemTests = 
  			new DynamicTestItem()
  				.getTests(item, includedElements, hp);	
  }
  private void addTestsToMenuItemContainer() {
  	menuItemTests.entrySet().forEach(s ->{
  		menuItemContainers.add(DynamicContainer.dynamicContainer(s.getKey(), s.getValue()));  		
  	});
  }
	private void addMenuItemContainerToMenuContainer(MenuItem item) {
		menuContainers.add(DynamicContainer.dynamicContainer(item.getName(), menuItemContainers));
	}
}
