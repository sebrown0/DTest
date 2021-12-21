package site_mapper.jaxb.classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * JAXB class representing the top level element root.
 * This will return a DynamicContainer with all the 
 * sub DynamicContainers found in the relevant XML doc.
 */
@XmlRootElement(name = "App")
public class App {		
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	private HomePage homePage;
		
	public DynamicContainer getTests() {
		List<DynamicContainer> appModules = new ArrayList<>();
		
		if(homepageOk() && modules != null) {			
			for (Module module : modules) {
				appModules.add(module.getModuleContainers(homePage));
			}			
		}else {
			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");			
		}
		return DynamicContainer.dynamicContainer("App", appModules);		
	}
		 
	private boolean homepageOk() {
		return (homePage != null) ? true : false;
	}
	public App setHomePage(HomePage homePage) {
		this.homePage = homePage;
		return this;
	}

	public List<Module> getModules() {
		return modules;
	}
	
}