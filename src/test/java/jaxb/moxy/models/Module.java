package jaxb.moxy.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;

/**
 * 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * HAVE TO GET/LOAD MODULE
 */
@XmlRootElement(name = "Module")
public class Module {	
	@XmlAttribute
	private String name;

  @XmlElementWrapper(name="Menus")
  @XmlElement(name="Menu")
  private List<Menu> menus;
  
  private List<DynamicContainer> moduleMenus = new ArrayList<>();
  
	public DynamicContainer getModuleContainers(HomePage hp) {  	
//	homePage.loadModule(name);
		if(menus != null) {
			menus.forEach(m -> {
				moduleMenus.add(m.getMenuContainers(hp));
	  	});	
		}  	
		return DynamicContainer.dynamicContainer(name, moduleMenus);
	}
  
  public String getName() {
      return name;
  }
	public List<Menu> getMenus() {
		return menus;
	}
	  
}
