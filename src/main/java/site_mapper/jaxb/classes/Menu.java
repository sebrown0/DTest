package site_mapper.jaxb.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.creators.PackageMaker;
import site_mapper.elements.IncludedElements;
import site_mapper.jaxb.classes.pom.PackageHierarchy;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get MenuItems' tests from the map returned from MenuItem.
 * @since 1.0
 * 
 * Menu found in a module.
 * The tests for each menu item, i.e. EmployeeDetails are
 * returned from MenuItem as a Map<String, List<DynamicTest>>.
 * 
 */
@XmlRootElement(name="Menu")
public class Menu {
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="package")
	private String packageName;	
  @XmlElement(name="MenuItem")
  private List<MenuItem> menuItems;

  
  // All the tests for each menu item.
  private Map<String, List<DynamicTest>> menuItemTests;;  
  // List of all test containers in the menu item.
  private List<DynamicContainer> menuContainers = new ArrayList<>();
  // List of individual test containers for each menu item. 
  private List<DynamicContainer> menuItemContainers = new ArrayList<>();
  
  public Menu getMenuContainers(PackageHierarchy ph) {
  	System.out.println("  Create menu package:" + name); // TODO - remove or log

		PackageMaker.makeWithPackageInfo(ph.addCurrent(name));
		
		if(menuItems != null) {
			menuItems.forEach(item -> {
				
				
				item.createPoms(ph);
			});	
		}		
		return this;
	}
//  public Menu getMenuContainers(String root, String prntPackage) {
//  	System.out.println("  Create menu package:" + name); // TODO - remove or log
//		if(menuItems != null) {
//			menuItems.forEach(item -> {
//				PackageMaker.makeWithPackageInfo(root, prntPackage, name);
//				item.createPoms();
//			});	
//		}		
//		return this;
//	}

  public DynamicContainer getMenuContainers(IncludedElements includedElements, HomePage hp, String moduleName) {
  	
		if(menuItems != null && includedElements != null) {
			menuItems.forEach(item -> {				
				getTestsForMenuItem(includedElements, item, hp, moduleName);	
				addTestsToMenuItemContainer();
				addMenuItemContainerToMenuContainer(item);
			});	
		}		
		return DynamicContainer.dynamicContainer(name, menuContainers);
	}
  private void getTestsForMenuItem(IncludedElements includedElements, MenuItem item, HomePage hp, String moduleName) {
  	menuItemTests = item.getTests(includedElements, hp, moduleName, packageName);			
  }
  private void addTestsToMenuItemContainer() {
  	menuItemTests.entrySet().forEach(s ->{
  		menuItemContainers.add(DynamicContainer.dynamicContainer(s.getKey(), s.getValue()));  		
  	});
  }
	private void addMenuItemContainerToMenuContainer(MenuItem item) {
		menuContainers.add(DynamicContainer.dynamicContainer(item.getName(), menuItemContainers));
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public String getName() {
		return name;
	}  
	public String getPackageName() {
		return packageName;
	}
}
