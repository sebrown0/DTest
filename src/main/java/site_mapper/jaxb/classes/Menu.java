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

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Menu found in a module.
 */
@XmlRootElement(name="Menu")
public class Menu {
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="package")
	private String packageName;	
  @XmlElement(name="MenuItem")
  private List<MenuItem> menuItems;
  
  private List<DynamicContainer> menuItemContainers = new ArrayList<>();
  private Map<String, List<DynamicTest>> menuItemTests;;
  
  public DynamicContainer getMenuContainers(HomePage hp, String moduleName) {  	
		if(menuItems != null) {
			menuItems.forEach(item -> {
				menuItemTests = item.getTests(hp, moduleName, packageName);
				getRequiredTests();
//				menuItemContainers.add(item.getDynamicContainer(hp, packageName, moduleName));
			});	
		}		
		return DynamicContainer.dynamicContainer(name, menuItemContainers);
	}
  
  private void getRequiredTests() {
  	menuItemTests.entrySet().forEach(s ->{
  		menuItemContainers.add(DynamicContainer.dynamicContainer(s.getKey(), s.getValue()));
  	});
  }
//	public DynamicContainer getMenuContainers(HomePage hp, String moduleName) {	
//		if(menuItems != null) {
//			menuItems.forEach(item -> {
//				menuItemContainers.add(item.getDynamicContainer(hp, packageName, moduleName));
//			});	
//		}		
//		return DynamicContainer.dynamicContainer(name, menuItemContainers);
//	}
	
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
