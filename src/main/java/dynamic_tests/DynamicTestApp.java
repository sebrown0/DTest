package dynamic_tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import app.xml_content.XmlContent;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.IncludedElements;
import site_mapper.elements.IncludedTests;
import site_mapper.jaxb.pom.Module;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Rename
 * @since 1.0
 * 
 * JAXB class representing the top level element root.
 * This will return a DynamicContainer with all the 
 * sub DynamicContainers found in the relevant XML doc.
 * 
 * TODO: Use SiteMapInfo.
 */

public class DynamicTestApp {
	
	private List<DynamicContainer> moduleMenus;
	
//	public DynamicContainer getAppTests() {
//		return DynamicContainer.dynamicContainer("ROOT", null, null);
//	}
	public DynamicContainer getAppTests(XmlContent content, HomePage hp) {
		
		if(homepageOk(hp) && content != null) {		
			moduleMenus = new ArrayList<>();
			/*
			 * TODO
			 * GET includedElements FROM XML CONTENT
			 * TODO
			 */
			IncludedElements includedElements = new IncludedTests(Arrays.asList("button"));
			
			List<Module> modules = content.getModules();
			
//			appModules.add(module.getModuleContainers(new IncludedTests(includeElementsForTest), homePage));
			
			if(modules != null) {
				modules.forEach(m -> {
//					List<Element> elements = m.getMenus().get(0).getMenuItems().get(0).getElements();
//					moduleMenus.add(m.getMenuContainers(includedElements, hp, name));
					moduleMenus.add(new DynamicTestModule().getModuleContainers(m, includedElements, hp));
		  	});	
			}	
		}
		  	
		return DynamicContainer.dynamicContainer("ROOT", moduleMenus);
	}
	
	 
//	public static DynamicContainer getTests(XmlContent content, HomePage homePage) {
////		includeElementsForTest.forEach(c -> System.out.println("->" + c));		
//		List<DynamicContainer> appModules = new ArrayList<>();		
//		List<Module> modules = content.getModules();
//		if(homepageOk(homePage) && modules != null) {			
//			for (Module module : modules) {
////				appModules.add(module.getModuleContainers(new IncludedTests(includeElementsForTest), homePage));
//			}			
//		}else {
//			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");			
//		}
//		return DynamicContainer.dynamicContainer("App", appModules);		
//	}
		 
	private static boolean homepageOk(HomePage homePage) {
		return true;
		/*
		 * UNCOMMENT AFTER TESTING
		 */
//		return (homePage != null) ? true : false;
	}
//	public DynamicTestApp setHomePage(HomePage homePage) {
//		this.homePage = homePage;
//		return this;
//	}
//
//	public List<Module> getModules() {
//		return modules;
//	}
	
}