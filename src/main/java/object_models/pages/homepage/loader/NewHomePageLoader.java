/**
 * 
 */
package object_models.pages.homepage.loader;

import org.openqa.selenium.WebDriver;

import object_models.pages.homepage.CoreDataLoader;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class NewHomePageLoader extends HomePageLoader{
	public NewHomePageLoader(WebDriver driver, HomePageElements elements) {
		super(driver, elements);

		super.setCoreData(new CoreDataLoader(driver));
	}

	@Override
	public HomePage loadHomePage() {
		
		super.setCoreData(new CoreDataLoader(driver));
		super.loadModule();
		super.loadCompany();
		super.hp = super.elements.getHomePage();
		super.initialiseHomePage(super.hp);
		super.setInitialStateOfContextManager();
		return super.hp;
	}

}
