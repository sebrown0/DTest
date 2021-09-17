/**
 * 
 */
package object_models.modules.personnel;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import object_models.modules.common.ModuleElements;
import object_models.navigation.left_side_menu.LeftMenu;
import object_models.navigation.top_right_nav_bar.NavBarPersonnelElements;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementStrategy;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLink;
import object_models.navigation.top_right_nav_bar.elements.quick_links.QuickLinkPersonnel;
import providers.ModuleNames;


/**
 * @author Steve Brown
 *
 */
public class PersonnelModuleLoader implements ModuleElements {
	private WebDriver driver;
	
	public PersonnelModuleLoader(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public NavBarElementStrategy getElementStrategy() {
		return new NavBarPersonnelElements(driver);
	}

	@Override
	public QuickLink getQuickLinkToLoadModule() {
		return new QuickLinkPersonnel(driver);
	}

	@Override
	public String getModuleName() {
		return ModuleNames.PERSONNEL_NAME;
	}

	@Override
	public void setLeftMenuElements(LeftMenu menu) {
		LogManager.getLogger().error("Not implemented");
		
	}
}
