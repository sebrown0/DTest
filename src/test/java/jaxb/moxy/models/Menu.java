package jaxb.moxy.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;

@XmlRootElement(name="Menu")
public class Menu {
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="package")
	private String packageName;	
  @XmlElement(name="MenuItem")
  private List<MenuItem> menuItems;
  
  private List<DynamicContainer> menuItemContainers = new ArrayList<>();
  
	public DynamicContainer getMenuContainers(HomePage hp) {	
		if(menuItems != null) {
			menuItems.forEach(item -> {
				menuItemContainers.add(item.getDynamicContainer(hp, packageName));
			});	
		}		
		return DynamicContainer.dynamicContainer(name, menuItemContainers);
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
