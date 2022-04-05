/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;

import dynamic_tests.elements.IncludedElements;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.menu.MenuType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestMenu {
  private List<DynamicContainer> menuTestItems = new ArrayList<>();  
  private MenuType menu;
  private IncludedElements includedElements;
  private HomePage hp;
  private String moduleName;  
  private String menuName;
  
  public DynamicTestMenu(MenuType menu, IncludedElements includedElements, HomePage hp, String moduleName) {
		this.menu = menu;
		this.includedElements = includedElements;
		this.hp = hp;
		this.moduleName = moduleName;
	}
  
	public DynamicContainer getMenuContainers() {  	
		if(menu.getMenuItems() != null && includedElements != null) {  	
  		menuName = menu.getPackageName();
			menu.getMenuItems().forEach(item -> {
				addMenuItemTestsToMenuContainer(item, getTestsForMenuItem(item));
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
		return DynamicContainer.dynamicContainer(menu.getName(), menuTestItems);
	}

	private void addMenuItemTestsToMenuContainer(MenuItem item, List<DynamicContainer> menuItemTestContainers) {
		menuTestItems.add(DynamicContainer.dynamicContainer(item.getName(), menuItemTestContainers));
	}
	
  private List<DynamicContainer> getTestsForMenuItem(MenuItem item) {
  	List<DynamicContainer> menuItemTestContainers = new ArrayList<>();		
  	item.setTestModuleName(moduleName).setTestMenuName(menuName);  	
 
 		new 
 			DynamicTestItem(includedElements, item, menuItemTestContainers, hp)
 				.addTests();	
 		
 		return menuItemTestContainers;
  }

	
}
