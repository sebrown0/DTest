/**
 * 
 */
package object_models.helpers;

import org.openqa.selenium.WebDriver;

/**
 * @author SteveBrown
 *
 */
public interface PageTitle {
	public default boolean isPageTitleCorrect(WebDriver driver, String title) {
		return (driver.getTitle().equals(title)) ? true : false;
	}
	
	public boolean isPageTitleCorrect();
}
