package object_models.left_menu.payroll;

import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextManager;
import object_models.forms.FormModal;
import object_models.helpers.Header;

/**
 * @author Steve Brown
 *
 */
public final class CloseAndLockPayroll extends FormModal {
	public static final String MENU_TITLE = "Close & Lock Payroll";
	public static final String PANEL_TITLE = "Close & Lock Payroll";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CloseAndLockPayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);
	}

	@Override
	public void close() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void waitForLoad() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setContextState() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public void setContainer() {
		logger.error("NOT IMPLEMENTED");		
	}
	@Override
	public Header getHeader() {
		// TODO Auto-generated method stub
		logger.error("NOT IMPLEMENTED");
		return null;
	}
//	@Override
//	public void setHeader() {
//		logger.error("NOT IMPLEMENTED");
//	}
	@Override
	public void setTitle() {
		logger.error("NOT IMPLEMENTED");
	}
	@Override
	public ContextId getContextId() {
		logger.error("NOT IMPLEMENTED");
		return null;
	}
	
	// Elements

	// Tabs
}