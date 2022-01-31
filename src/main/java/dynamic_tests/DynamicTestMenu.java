/**
 * 
 */
package dynamic_tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import object_models.pages.homepage.HomePage;
import site_mapper.elements.IncludedElements;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Menu;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestMenu {
	private Map<String, List<DynamicTest>> menuItemTests;;  
  // List of all test containers in the menu item.
  private List<DynamicContainer> menuContainers = new ArrayList<>();
  // List of individual test containers for each menu item. 
  private List<DynamicContainer> menuItemContainers = new ArrayList<>();
  
  private String menuName;
  
  public DynamicContainer getMenuContainers(
  		Menu menu, IncludedElements includedElements, HomePage hp, String moduleName) {
  	
//		if(menu.getMenuItems() != null && includedElements != null) {
  	if(menu.getMenuItems() != null) {
  		menuName = menu.getPackageName();
			menu.getMenuItems().forEach(item -> {				
				getTestsForMenuItem(includedElements, item, hp, moduleName);	
				addTestsToMenuItemContainer();
				addMenuItemContainerToMenuContainer(item);
			});	
		}		
		return DynamicContainer.dynamicContainer(menu.getName(), menuContainers);
	}
  private void getTestsForMenuItem(IncludedElements includedElements, MenuItem item, HomePage hp, String moduleName) {
//  	menuItemTests = item.getTests(includedElements, hp, moduleName, "packageName"); //TODO
  	item.setTestModuleName(moduleName);
  	item.setTestMenuName(menuName);
  	menuItemTests = new DynamicTestItem().getTests(item, item.getElements(), includedElements, hp, moduleName, moduleName);	
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
