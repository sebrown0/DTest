package jaxb.moxy.models;

import java.util.Collection;
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
@XmlRootElement(name = "MODULE")
public class Module {	
	@XmlAttribute
	private String name;

  @XmlElementWrapper(name="Menus")
  @XmlElement(name="Menu")
  private List<Menu> menus;
  
  public void runModuleTests(HomePage homePage) {
  	homePage.loadModule(name);
  	if(menus != null) {
  		menus.forEach(m -> {
    		System.out.println(" Running tests for menu: " + m.getName()); // TODO - remove or log 	
    		m.runMenuItemTests();
    	});	
  	}  	
  }
  
  public String getName() {
      return name;
  }
	public List<Menu> getMenus() {
		return menus;
	}

	public void getTests(HomePage homePage, Collection<DynamicContainer> containers) {
		homePage.loadModule(name);
  	if(menus != null) {
  		menus.forEach(m -> {
  			//add menu items to container
    		System.out.println(" Running tests for menu: " + m.getName()); // TODO - remove or log 	
    		m.runMenuItemTests();
    	});	
  	}
	}
	  
}
