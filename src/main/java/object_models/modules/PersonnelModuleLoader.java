/**
 * 
 */
package object_models.modules;

import org.openqa.selenium.WebDriver;

import exceptions.ElementDoesNotExistException;
import object_models.navigation.left_side_menu.ZZZ_LeftMenu;
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
	public ZZZ_LeftMenu getLeftMenu() throws ElementDoesNotExistException {		
		throw new ElementDoesNotExistException("Left menu for [" + getModuleName() + "] does not exist");		
	}

//	@Override
//	public LeftMenu getLeftMenu() {
//		// TODO Auto-generated method stub
//		return new NoElement(getModuleName());
//	}	
}
