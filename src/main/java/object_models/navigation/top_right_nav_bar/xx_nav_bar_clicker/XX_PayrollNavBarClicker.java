/**
 * 
 */
package object_models.navigation.top_right_nav_bar.xx_nav_bar_clicker;

import exceptions.ElementDoesNotExistException;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;

/**
 * @author Steve Brown
 *
 */
public final class XX_PayrollNavBarClicker extends XX_NavBarClicker {

	public XX_PayrollNavBarClicker(NavBarElementStrategy elementStrategy) {
		super(elementStrategy);
	}

	public void clickEmployeeCreation() throws ElementDoesNotExistException {
		click(NavBarEmployeeCreation.ORIGINAL_NAME);		
	}
}
