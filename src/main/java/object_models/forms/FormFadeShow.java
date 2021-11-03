/**
 * 
 */
package object_models.forms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextManager;
import object_models.helpers.ButtonClicker;
import object_models.helpers.title.TitleModalFadeShow;

/**
 * @author Steve Brown
 *
 */
public class FormFadeShow extends FormModal {
	private WebElement topLevelContainer;
	private WebElement header;
	private String panelTitle;
	
	public static final String MENU_TITLE = "None";
	public static final String PANEL_TITLE = "None";
	
	public FormFadeShow(WebDriver driver, ContextManager contextManager) {
		super(driver, "None", contextManager);
		setMyContainers();
	}
	
	public FormFadeShow(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		super(driver, expectedTitle, contextManager);		
	}
	
	@Override
	public void waitForLoad() {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		topLevelContainer = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("div[class='modal fade show']")));
	}
	
	@Override
	public void setTitle() {
		super.title = new TitleModalFadeShow("None", driver);
	}
		
	@Override
	public void close() {
		ButtonClicker.clickUntilNotVisible(driver, By.className("close"), 25);
		contextManager.deleteCurrentContextAndRevertToCallingContext();
	}
	
	@Override
	public ContextId getContextId() {
		return new ContextId(this.getClass().getSimpleName(), "None"); // TODO - IMPLEMENT 
	}

	@Override
	public void setTopContainer() {
		//check that this is correct and that this isn't a child of body[class='modal-open']
		super.formContainerElement = driver.findElement(By.cssSelector("div[class='modal fade show']"));
	}

	@Override
	public void setMyContainers() {
		header = topLevelContainer.findElement(By.cssSelector("div[class='modal-header']"));
		panelTitle = header.findElement(By.className("modal-title")).getText().trim();
		/*
		 * This will set the correct title but the context id won't be set.
		 */
		super.title.setExpected(panelTitle); 	
	}
	
}
