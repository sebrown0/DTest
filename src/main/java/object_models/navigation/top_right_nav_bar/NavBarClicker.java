/**
 * 
 */
package object_models.navigation.top_right_nav_bar;

import java.util.Map;

import exceptions.ElementDoesNotExistException;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;

/**
 * @author Steve Brown
 *
 */
public class NavBarClicker {
	private Map<String, NavBarElement> elements;	
	
	public NavBarClicker(NavBarElementStrategy elementStrategy) {
		elements = elementStrategy.getElements();
	}
	
	public void employeeCreation() throws ElementDoesNotExistException {
		click(NavBarEmployeeCreation.ORIGINAL_NAME);		
	}
	
	private void click(String elementName) throws ElementDoesNotExistException {
		NavBarElement el = elements.get(elementName);
		if (elementExists(el.getOriginalName())) {
			el.clickElement();	
		}else {
			throw new ElementDoesNotExistException("Element [%s] does not exist.");
		}
	}
	
	private boolean elementExists(String elementName) {
		return (elements.containsKey(elementName)) ? true : false;			
	}
}
