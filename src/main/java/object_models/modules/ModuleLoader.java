/**
 * 
 */
package object_models.modules;

import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;

/**
 * @author Steve Brown
 *
 */
public interface ModuleLoader {
	void loadModule(); 
	String getModuleName();
	NavBarElementStrategy getElementStrategy();
}
