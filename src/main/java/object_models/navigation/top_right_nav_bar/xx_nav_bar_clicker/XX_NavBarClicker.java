/**
 * 
 */
package object_models.navigation.top_right_nav_bar.xx_nav_bar_clicker;

import java.util.Map;

import exceptions.ElementDoesNotExistException;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;

/**
 * @author Steve Brown
 *
 */
public abstract class XX_NavBarClicker {
	private Map<String, NavBarElement> elements;	
	
	public XX_NavBarClicker(NavBarElementStrategy elementStrategy) {
		elements = elementStrategy.getElements();
	}
		
	protected void click(String elementName) throws ElementDoesNotExistException {
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
