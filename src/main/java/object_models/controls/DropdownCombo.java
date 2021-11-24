/**
 * 
 */
package object_models.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.states.StateHeaderForm;
import controls.Control;
import object_models.forms.FormHeader;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Header;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
@SuppressWarnings("unused")
public class DropdownCombo extends FormWithIFrame implements Control {
	private WebElement table;	
	
	private static final By byTable = By.id("myGrid1");
	
	public static final String MENU_TITLE = "Combos";
	public static final String PANEL_TITLE = "Dropdown Combo";
	
	public DropdownCombo(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-IPORTAL_POPUPS_MEDIUM4", contextManager);
	
		setMyContainers();
	}

	/*
	 * 
	 * TODO - Add elements and actions.
	 * This is only the bare bones of the element.
	 * 
	 */
	@Override
	public void setMyContainers() {
		super.switchToIFrame();
		table = super.driver.findElement(byTable);
	}
	
	@Override
	public Header getHeader() {
		contextManager.switchToStateInCurrentContext(StateHeaderForm.class);
		return new FormHeader(super.formContainerElement);
	}

	@Override
	public ContextId getContextId() {		
		return new ContextId(PANEL_TITLE, "None");
	}
	// Elements
	// Actions
}
