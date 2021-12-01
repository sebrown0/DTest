/**
 * 
 */
package object_models.forms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import object_models.helpers.ElementClicker;
import object_models.helpers.title.TitleModalFadeShow;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * <p>
 * TODO - If this is added to the context queue
 * it can cause problems if it is the penultimate
 * context in the queue and the last object is removed.
 * The current context will switch back to this and
 * it's likely that it won't exist in the DOM anymore.
 * 
 * Therefore, we need some sort of 'garbage collector'
 * in the CM to tidy up missing elements/contexts. 
 * </p> 
 */
public class FormFadeShow extends FormModal {
	private WebElement topLevelContainer;
	private WebElement header;
	private String panelTitle;
	
	public static final String MENU_TITLE = "None";
	public static final String PANEL_TITLE = "None";
	
	public FormFadeShow(CoreData coreData) {
		super(coreData, "None");		
	}
	public FormFadeShow(CoreData coreData, String expectedTitle) {
		super(coreData, expectedTitle);		
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
		ElementClicker.clickUntilNotVisible(driver, By.className("close"), 25);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='modal fade show']")));
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
