/**
 * 
 */
package object_models.forms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextManager;
import object_models.helpers.ButtonClicker;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.Title;
import object_models.helpers.title.TitleModalFadeShow;

/**
 * @author Steve Brown
 *
 */
public class FormFadeShow extends FormModal {
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
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("div[class='modal fade show']")));
	}
	
	@Override
	public void setTitle() {
		super.title = new TitleModalFadeShow("None", driver);
	}
	
	@Override
	public PageTitle getTitle() {
		return new Title("", driver, By.className("modal-body"));
	}
		
	@Override
	public void close() {
		ButtonClicker.clickUntilNotVisible(driver, By.className("close"), 25);
		System.out.println("FormFadeShow -> close() -> CHECK THIS IS WORKING CORRECTLY"); // TODO - remove or log 	
		//contextManager.closeCurrentContext();
	}
	
	@Override
	public ContextId getContextId() {
		return new ContextId(this.getClass().getSimpleName(), "None"); // TODO - IMPLEMENT 
	}

	@Override
	public void setTopContainer() {
		//check that this is correct and that this isn't a child of body[class='modal-open']
		super.formContainerElement = driver.findElement(By.className("div[class='modal fade show']"));
	}

	@Override
	public void setMyContainers() {
		// None		
	}
	
}
