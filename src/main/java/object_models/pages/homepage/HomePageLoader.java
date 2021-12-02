/**
 * 
 */
package object_models.pages.homepage;

import org.openqa.selenium.WebDriver;

import entities.Company;
import factories.ModuleElementsFactory;
import object_models.modules.common.ModuleElements;
import object_models.modules.common.ModuleLoader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class HomePageLoader {
	public static HomePage loadHomePage(String forModule, Company forCompany, WebDriver driver) {
		CoreData coreData = new CoreDataLoader(driver);
		ModuleElements moduleElements = ModuleElementsFactory.getModuleElements(forModule, forCompany);
		moduleElements.setCoreData(coreData);
		return loadModule(moduleElements, coreData);
	}
	
	private static HomePage loadModule(ModuleElements moduleElements, CoreData coreData) {		
		ModuleLoader moduleLoader = new ModuleLoader(coreData, moduleElements);
		HomePage hp = moduleLoader.loadModule(moduleElements.getModuleName());			
		return hp;
	}
}
