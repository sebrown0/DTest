/**
 * 
 */
package object_models.controls;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import context_manager.states.StateHeaderForm;
import controls.Control;
import object_models.forms.FormHeader;
import object_models.forms.FormModal;
import object_models.forms.FormWithIFrame;
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
	private WebElement topLevelContainer;
	private WebElement table;	
	
	private static final By byTopLevelContainer = By.cssSelector("body > form > div.container-fluid");
	private static final By byTable = By.id("myGrid1");
	
	public static final String MENU_TITLE = "Combos";
	public static final String FORM_TITLE = "Dropdown Combo";
	
	public DropdownCombo(WebDriver driver, Reload reloadEmpDetails, ContextManager contextManager) {
		super(driver, FORM_TITLE, "_iframex-IPORTAL_POPUPS_MEDIUM4", contextManager);
	
		this.reloadEmpDetails = reloadEmpDetails;
	}

	@Override
	public void waitForLoad() {
		super.wait.until(ExpectedConditions.visibilityOfElementLocated(byTopLevelContainer));
	}
	@Override
	public void close() {
		logger.error("NOT IMPLEMENTED");
	}
	
	@Override
	public void setContextState() {
		ContextState con = contextManager.getCurrentContext();		
		con.setState(new StateHeaderForm(con));
	}
	@Override
	public void setContainer() {
		topLevelContainer = driver.findElement(byTopLevelContainer);
		table = topLevelContainer.findElement(byTable);
	}
	@Override
	public void setHeader() {
//		super.header = new FormHeader(topLevelContainer);
//		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setTitle() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public ContextId getContextId() {		
		return new ContextId(FORM_TITLE, "None");
	}

}
