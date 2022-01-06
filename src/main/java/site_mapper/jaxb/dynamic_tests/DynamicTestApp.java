package site_mapper.jaxb.dynamic_tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.IncludedTests;

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
@XmlRootElement(name = "SiteMap")
public class DynamicTestApp {
	//Added 06/01/2022 NOT TESTED
//	@XmlElement(name="Info")
//	private SiteMapInfo siteMapInfo;
	
	@XmlElementWrapper(name="IncludeElementsForTest")
	@XmlElement(name="Include")
	private List<String> includeElementsForTest;
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	private HomePage homePage;
		
	public DynamicContainer getTests() {
//		includeElementsForTest.forEach(c -> System.out.println("->" + c));		
		List<DynamicContainer> appModules = new ArrayList<>();		
		if(homepageOk() && modules != null) {			
			for (Module module : modules) {
				appModules.add(module.getModuleContainers(new IncludedTests(includeElementsForTest), homePage));
			}			
		}else {
			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");			
		}
		return DynamicContainer.dynamicContainer("App", appModules);		
	}
		 
	private boolean homepageOk() {
		return (homePage != null) ? true : false;
	}
	public DynamicTestApp setHomePage(HomePage homePage) {
		this.homePage = homePage;
		return this;
	}

	public List<Module> getModules() {
		return modules;
	}
	
}