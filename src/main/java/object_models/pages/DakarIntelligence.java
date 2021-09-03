/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.WebDriver;

import object_models.helpers.PageTitle;

/**
 * @author Steve Brown
 *
 */
public class DakarIntelligence extends Page{
	

	public DakarIntelligence(WebDriver driver, PageTitle title) {
		super(driver, title);
		// TODO Auto-generated constructor stub
	}

	public static final String PAGE_TITLE = "Reports";
	

}
