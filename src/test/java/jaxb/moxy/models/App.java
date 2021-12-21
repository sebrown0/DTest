package jaxb.moxy.models;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;

@XmlRootElement(name = "App")
public class App implements DynamicContainerSetter {
		
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	private HomePage homePage;
//	private DynamicContainer app;
	
	public List<Module> getModules() {
		return modules;
	}
	
//**********************************************************************
//	List<DynamicContainer> getTestCont(String contName, List<DynamicTest> tests) {
//		return Arrays.asList(DynamicContainer.dynamicContainer(contName, tests), DynamicContainer.dynamicContainer(contName, tests));
//	}
//	List<DynamicTest> getTestTests(){
//		return Arrays.asList(
//				dynamicTest("MenuItem T1", ()->{ assertTrue(true); }),
//				dynamicTest("MenuItem T2", ()->{ assertTrue(true); }));	
//	}
	//**********************************************************************
	
	public DynamicContainer getTests() {
		List<DynamicContainer> appModules = new ArrayList<>();
//		appModules= getTestCont("name", getTestTests());
		
		if(homepageOk() && modules != null) {			
			for (Module module : modules) {
				appModules.add(module.getModuleContainers(homePage));
			}			
		}else {
			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");
			System.out.println("Homepage is null. Cannot run tests"); 	
		}
		return DynamicContainer.dynamicContainer("App", appModules);		
	}
	
//	public List<DynamicContainer> getTests() {
//		List<DynamicContainer> appModules = new ArrayList<>();
//		
//		if(homepageOk() && modules != null) {			
//			for (Module module : modules) {
//				appModules.add(module.getModuleContainers(homePage));
//			}			
//		}else {
//			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");
//			System.out.println("Homepage is null. Cannot run tests"); 	
//		}
//		return appModules;		
//	}
	 
	private boolean homepageOk() {
		return (homePage != null) ? true : false;
	}
	public App setHomePage(HomePage homePage) {
		this.homePage = homePage;
		return this;
	}

	@Override
	public String getName() {
		return "App";
	}

	@Override
	public DynamicContainer getContainer() {
		//NOT USING - EMOVE!!!!!!!!!!!!!!!!!!!!
		return null;
//		return app;
	}
}