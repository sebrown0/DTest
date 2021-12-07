/**
 * 
 */
package factories;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LocatorFactory {
	public static By getLocator(String by, String locator) {
		By byLoc = null;
		if(by.equals("css")) {
			byLoc = getLocatorForCSS(locator);
		}else if (by.equals("xpath")) {
			byLoc = getLocatorForXpath(locator);
		}else {
			LogManager.getLogger().error("Get locator by[" + by + "] not implemented");
		}
		
		return byLoc;
	}
	
	private static By getLocatorForCSS(String locator){
		return By.cssSelector(locator);
	}
	
	private static By getLocatorForXpath(String locator){
		return By.xpath(locator);
	}
}
