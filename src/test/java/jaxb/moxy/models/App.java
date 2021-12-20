package jaxb.moxy.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;

@XmlRootElement(name = "App")
public class App {
	private HomePage homePage;
	
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	public List<Module> getModules() {
		return modules;
	}
	
	public Collection<DynamicContainer> getTests() {
		Collection<DynamicContainer> containers = new ArrayList<DynamicContainer>();
		
		if(homepageOk() && modules != null) {			
			for (Module module : modules) {
//				containers.add((DynamicContainer) module.getTests(homePage, containers));
				// Pass the container for tests to be added.
				module.getTests(homePage, containers);
			}			
		}else {
			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");
			System.out.println("Homepage is null. Cannot run tests"); 	
		}
		return containers;		
	}
	
	public void runTests() {
		if(homepageOk()) {			
			getModules().forEach(m -> {
				System.out.println("Running tests for: " + m.getName()); // TODO - remove or log
				m.runModuleTests(homePage);
			});	
		}else {
			LogManager.getLogger().error("Homepage is null. Cannot run tests");
			System.out.println("Homepage is null. Cannot run tests"); 	
		}
		
//		System.out.println("Running tests.."); // TODO - remove or log 	
	}
 
	private boolean homepageOk() {
		return (homePage != null) ? true : false;
	}
	public App setHomePage(HomePage homePage) {
		this.homePage = homePage;
		return this;
	}
}