/**
 * 
 */
package object_models.pages.homepage.loader;

import org.openqa.selenium.WebDriver;

import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.CoreDataLoader;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class ExistingHomePageLoader extends HomePageLoader{

	public ExistingHomePageLoader(CoreData coreData, WebDriver driver, HomePageElements elements, HomePage hp) {
		super(driver, elements);
		
		super.setCoreData(coreData);
		super.setCurrentCompany(hp.getCurrentCompany());
		super.hp = hp;
	}

	@Override
	public HomePage loadHomePage() {
		//TODO - are we using the correct version HP?
		if(haveLoadedModuleOrCompany()) {
			super.setInitialStateOfContextManager();
			createNewHomePage();
		}		
		return hp;		
	}

	// Only load if not null and different from existing module/company.
	private boolean haveLoadedModuleOrCompany() {		
		return (super.loadModule() || super.loadCompany()) ? true : false;
	}

	private void createNewHomePage() {
		super.setCoreData(new CoreDataLoader(driver));
		super.hp = super.elements.getHomePage();
		super.initialiseHomePage(super.hp);
	}
	
}
