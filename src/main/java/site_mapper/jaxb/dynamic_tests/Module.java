package site_mapper.jaxb.dynamic_tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.IncludedElements;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Module found in the app XML.
 */
@XmlRootElement(name = "Module")
public class Module {	
	@XmlAttribute(name="name")
	private String name;
  @XmlElementWrapper(name="Menus")
  @XmlElement(name="Menu")
  private List<Menu> menus;
  
  private List<DynamicContainer> moduleMenus;
  
//  public Module getModuleContainers(PackageHierarchy ph, final SiteMapInfo siteMap) {  	
//  	PackageMaker.makeWithPackageInfo(siteMap, ph.reset().addCurrent(name));
//		if(menus != null) {			
//			menus.forEach(m -> {								 	
//				m.getMenuContainers(siteMap, ph);
//	  	});	
//		}  	
//		return this;
//	}
  
	public DynamicContainer getModuleContainers(IncludedElements includedElements, HomePage hp) {
		moduleMenus = new ArrayList<>();
		if(menus != null) {
			menus.forEach(m -> {
				moduleMenus.add(m.getMenuContainers(includedElements, hp, name));
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
