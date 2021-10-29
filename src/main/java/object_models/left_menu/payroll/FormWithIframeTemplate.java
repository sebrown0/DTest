package object_models.left_menu.payroll;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Header;
import object_models.helpers.title.TitlePanel;

@SuppressWarnings("unused")
public class FormWithIframeTemplate extends FormWithIFrame {	
	private WebElement container;	
	private WebDriverWait waitForMsg;
	
	public static final String MENU_TITLE = "TODO";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "TODO";	
	
	public FormWithIframeTemplate(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "TODO", contextManager);
		setMyContainers();
	}
		
	public String getIframeTitle() {
		WebElement e = driver.findElement(By.cssSelector("TODO"));
		return e.getText();
	}

	@Override
	public void setMyContainers() {
		// None		
	}

	@Override
	public Header getHeader() {
		// TODO Auto-generated method stub
		logger.error("NOT IMPLEMENTED");
		return null;
	}
//	@Override
//	public void close() {
//		// TODO OVERRIDE IF NECESSARY
//	}
//	// Override if the form should wait for a different element.
//	@Override
//	protected void waitForLoad() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(byFormContainer));
//	}	
//	// Override this if the top element of the form is different from byFormContainer
//	@Override
//	protected void setTopContainer() {
//		formContainerElement = driver.findElement(byFormContainer);
//	}
//	// Override if title is different.
//	@Override
//	protected void setTitle() {
//		title = new TitlePanel(expectedTitle, driver);
//	}
	
	// Actions
	
	

	// Elements

	
}