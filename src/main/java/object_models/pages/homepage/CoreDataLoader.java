/**
 * 
 */
package object_models.pages.homepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * The core objects required by the app.
 */
public class CoreDataLoader implements CoreData {
	private WebDriver driver;
	
	public CoreDataLoader(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebDriver getWebDriver() {
		return driver;
	}
	@Override
	public ContextManager getContextManager() {
		return new ContextManager(driver);
	}
	@Override
	public Logger getLogger() {
		return LogManager.getLogger();
	}

}
