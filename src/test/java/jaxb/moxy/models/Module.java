package jaxb.moxy.models;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

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
  
  private List<DynamicContainer> moduleMenus = new ArrayList<>();
  
//**********************************************************************
	List<DynamicContainer> getTestCont(String contName, List<DynamicTest> tests) {
		return Arrays.asList(DynamicContainer.dynamicContainer(contName, tests));
	}
	List<DynamicTest> getTestTests(){
		return Arrays.asList(
				dynamicTest("Menu 1", ()->{ assertTrue(true); }),
				dynamicTest("Menu 2", ()->{ assertTrue(true); }));	
	}
	//**********************************************************************
	
  
	//HAS TO RETURN A DynamicContainer WITH LIST OF DynamicContainerS
	public DynamicContainer getModuleContainers(HomePage hp) {
  	
//	homePage.loadModule(name);
	if(menus != null) {
		menus.forEach(m -> {
			moduleMenus.add(m.getMenuContainers(hp));
//			moduleMenus.addAll(getTestCont(m.getName(), getTestTests()));

  	});	
	}  	
	return DynamicContainer.dynamicContainer(name, moduleMenus);
}
//  public List<DynamicContainer> getModuleContainers(HomePage homePage) {
//  	
////  	homePage.loadModule(name);
//  	if(menus != null) {
//  		menus.forEach(m -> {
//  			moduleMenus.addAll(getTestCont(m.getName(), getTestTests()));
////  			moduleMenus.add(DynamicContainer.dynamicContainer(name, getTestTests()));
////  			moduleMenus.add(DynamicContainer.dynamicContainer(name, m.getMenuContainers()));
//    	});	
//  	}  	
//  	return moduleMenus;
//  }
  
  public String getName() {
      return name;
  }
	public List<Menu> getMenus() {
		return menus;
	}

//	public void getTests(HomePage homePage, Collection<DynamicContainer> containers) {
//		homePage.loadModule(name);
//  	if(menus != null) {
//  		menus.forEach(menu -> {
//  			//add menu items to container
//    		System.out.println(" Running tests for menu: " + menu.getName()); // TODO - remove or log 	
//    		menu
//    			.setHomePage(homePage)
//    			.setTestContainers(containers)
//    			.getMenuContainers();
//    	});	
//  	}
//	}
	  
}
