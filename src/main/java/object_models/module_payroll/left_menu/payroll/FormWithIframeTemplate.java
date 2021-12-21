package object_models.module_payroll.left_menu.payroll;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.states.StateHeaderForm;
import object_models.forms.FormHeader;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Header;
import object_models.pages.homepage.CoreData;

@SuppressWarnings("unused")
public class FormWithIframeTemplate extends FormWithIFrame {	
	// REMOVE IF NOT USING!!!!!!!
	private WebElement container;	
	private WebDriverWait waitForMsg;
	
	public static final String MENU_TITLE = "TODO";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "TODO";	
	
	public FormWithIframeTemplate(CoreData coreData) {
		super(coreData, PANEL_TITLE, "TODO IfRAME NAME");
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