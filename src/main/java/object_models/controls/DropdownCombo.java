/**
 * 
 */
package object_models.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.StateHeaderForm;
import controls.Control;
import object_models.forms.FormHeader;
import object_models.forms.FormWithIFrame;
import object_models.helpers.Header;
import object_models.helpers.IFrame;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 */
@SuppressWarnings("unused")
public class DropdownCombo extends FormWithIFrame implements Control {
	private IFrame iFrame;
	private Reload reloadEmpDetails;
	private WebElement container;
	private WebElement table;	
	
//	private static final By byContainer = By.cssSelector("body > form > div.container-fluid");
	private static final By byContainer = By.cssSelector("body[class='modal-open']");
	private static final By byTable = By.id("myGrid1");
	
	public static final String MENU_TITLE = "Combos";
	public static final String PANEL_TITLE = "Dropdown Combo";
	
	public DropdownCombo(WebDriver driver, Reload reloadEmpDetails, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-IPORTAL_POPUPS_MEDIUM4", contextManager);
	
		this.reloadEmpDetails = reloadEmpDetails;
	}

	@Override
	public void waitForLoad() {
		super.wait.until(ExpectedConditions.visibilityOfElementLocated(byContainer));
	}
	@Override
	public void close() {
		logger.error("NOT IMPLEMENTED");
	}	
	@Override
	public void setContextState() {
		ContextState con = contextManager.getLastContext();		
		con.setState(new StateHeaderForm(contextManager, container, By.className("modal-header"), driver));
	}
	@Override
	public void setContainer() {
		container = driver.findElement(byContainer);
		table = container.findElement(byTable);
	}
	@Override
	public Header getHeader() {
		contextManager.switchToStateInCurrentContext(StateHeaderForm.class);
		return new FormHeader(container);
//		super.header = new FormHeader(topLevelContainer);
//		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setTitle() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public ContextId getContextId() {		
		return new ContextId(PANEL_TITLE, "None");
	}
	
	/*
	 * IF GETTING HEADER SWITCH TO IT
	 * IF GETTING ITEM ON FORM SWITCH TO IFRAME
	 */

}
