package object_models.left_menu.payroll;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.states.StateHeaderForm;
import object_models.forms.FormHeader;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Header;
import object_models.helpers.title.TitlePanel;

@SuppressWarnings("unused")
public class FormWithIframeTemplate extends FormWithIFrame {	
	// REMOVE IF NOT USING!!!!!!!
	private WebElement container;	
	private WebDriverWait waitForMsg;
	
	public static final String MENU_TITLE = "TODO";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "TODO";	
	
	public FormWithIframeTemplate(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "TODO IfRAME NAME", contextManager);
		setMyContainers();
	}
		
	@Override
	public void setMyContainers() {
		super.switchToIFrame();
		// ADD ELEMENT CONTAINERS
	}
	
	@Override
	public Header getHeader() {
		contextManager.switchToStateInCurrentContext(StateHeaderForm.class);
		//IF IT'S NOT A FormHeader CHANGE IT!
		return new FormHeader(super.formContainerElement);
	}

	@Override
	public ContextId getContextId() {		
		return new ContextId(PANEL_TITLE, "None");
	}
	
	// Actions	

	// Elements
	
}