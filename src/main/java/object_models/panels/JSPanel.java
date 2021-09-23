/**
 * 
 */
package object_models.panels;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.forms.ContainerAction;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public class JSPanel implements ContainerAction { // ContainerAction extends Closable (was ChildElement)
	protected WebDriver driver;
	private PageTitle title = null;
	private String expectedTitle;
	private Logger logger = LogManager.getLogger();
	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
	
	public JSPanel(WebDriver driver, String expectedTitle) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
		
		try {
			waitForLoad();
		} catch (Exception e) {
			logger.error("Could not load panel [" + expectedTitle + "]");
			close();
		}
		setTitle();
	}

	private void waitForLoad() throws Exception {
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
	public void close() {
		CloserPanel closer = new CloserPanel(driver);
		try {
			closer.close();
		} catch (Exception e) {
			logger.error("Could not close panel [" + expectedTitle + "]");
		}
	}

}
