/**
 * 
 */
package object_models.panels;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.forms.ContainerAction;
import object_models.helpers.ChildElement;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public class JSPanel implements ContainerAction , ChildElement { // remove ChildElement
	protected WebDriver driver;
	private PageTitle title = null;
	private String expectedTitle;
	
	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
	
	public JSPanel(WebDriver driver, String expectedTitle) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
		
		waitForLoad();
		setTitle();
	}

	private void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", expectedTitle));		
	}

	private void setTitle() {
		title = new TitlePanel(expectedTitle, driver);
	}
	
	@Override
	public PageTitle getTitle() {
		return title;
	}

	@Override
	public void closeElement() {
		CloserPanel closer = new CloserPanel(driver);
		closer.close();
	}

}
