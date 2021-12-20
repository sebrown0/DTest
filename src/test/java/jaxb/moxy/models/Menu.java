package jaxb.moxy.models;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Menu")
public class Menu {
	@XmlAttribute
	private String name;
	
//	@XmlElementWrapper(name="Nodes")
  @XmlElement(name="MenuItem")
  private List<MenuItem> menuItems;

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public String getName() {
		return name;
	}
  
	public void runMenuItemTests() {
		if(menuItems != null) {
			menuItems.forEach(item -> {
				System.out.println("  Running tests for menu item: " + item.getName()); // TODO - remove or log 	
				item.runMenuItemTests();
			});	
		}		
	}
	
}
