/**
 * 
 */
package object_models.pages.homepage.loader;

import org.openqa.selenium.WebDriver;

import entities.company.Company;
import factories.ModuleElementsFactory;
import object_models.modules.common.ModuleLoader;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.CoreDataLoader;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * Get a home page for 
 *  1. When a module is loaded (if it's the same module return same HP).
 *  2. A company is loaded (if it's the same company return same HP).
 *  3. Both of the above.
 */

public class ZZZ_HomePageLoader {
	public static HomePage loadHomePage(String forModule, Company forCompany, WebDriver driver) {
		CoreData coreData = new CoreDataLoader(driver);
		HomePageElements moduleElements = ModuleElementsFactory.getModuleElements(forModule, forCompany);
		moduleElements.setCoreData(coreData);
		return loadModule(moduleElements, coreData);
	}
	
	private static HomePage loadModule(HomePageElements moduleElements, CoreData coreData) {		
		ModuleLoader moduleLoader = new ModuleLoader(coreData, moduleElements);
//		HomePage hp = 
				moduleLoader.loadModule(moduleElements.getModuleName());			
		return null;
	}
}
