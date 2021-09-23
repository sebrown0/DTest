/**
 * 
 */
package object_models.pages;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import object_models.forms.ContainerAction;
import object_models.helpers.ChildElement;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePage;

/**
 * @author Steve Brown
 *
 */
public class Page implements ContainerAction, ChildElement {
	protected WebDriver driver;
	private PageTitle title;	
//	private ContainerAction pageElement;
	private String expectedTitle;
	
	public Page(WebDriver driver, String expectedTitle) {
		this.driver = driver;
	}
	
	public void close() {
//		pageElement.closeElement();
	}

	@Override
	public void closeElement() {
		LogManager.getLogger().error("closeElement not implemented for Page");
	}

	@Override
	public PageTitle getTitle() {
		if(title == null) {
			title = new TitlePage(expectedTitle, driver);
		}
		return title;
	}
		
}
