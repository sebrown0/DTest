/**
 * 
 */
package object_models.modules;

import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.TopRightNavBar;

/**
 * @author Steve Brown
 *
 */
public interface ModuleLoader {
	void loadModule(TopRightNavBar topRightNavBar, LeftMenu leftMenu);	
	String getModuleName();
}
