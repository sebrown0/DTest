package object_models.module_payroll.left_menu.payroll;

import object_models.forms.FormModal;
import object_models.pages.homepage.CoreData;

/**
 * @author Steve Brown
 *
 */
public final class CloseAndLockPayroll extends FormModal {
	public static final String MENU_TITLE = "Close & Lock Payroll";
	public static final String PANEL_TITLE = "Close & Lock Payroll";
	public static final String MENU_PARENT_NAME = "Payroll";
	
	public CloseAndLockPayroll(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		setMyContainers();
	}
	
	@Override
	public void setMyContainers() {
		// None		
	}
	
	// Elements

	// Tabs
}