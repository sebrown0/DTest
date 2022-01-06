/**
 * 
 */
package site_mapper.jaxb.dynamic_tests;

import java.util.Optional;

import logging.LogFactory;
import object_models.forms.ContainerAction;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementClass;
import site_mapper.elements.SiteMapElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 * 	Rename and refactor.
 * @version 1.2
 * 	Change the way the menu element is found.
 * @since 1.0
 * 
 */
public class SiteMapElementLoader {
	private static String[] parts;
	private static String packageName;
	
	public static SiteMapElement getAndLoadSiteMapElement(ElementClass nodeClass, HomePage hp, Class<?> clazz) {
		SiteMapElement siteElement = null;
				
		if(nodeClass != null){
			packageName = nodeClass.getParentPackage();			
			loadModuleIfRequired(nodeClass, hp);
			
			if(isLeftMenu()) {			
				siteElement = loadLeft(hp, clazz);		
			}else if (isTopRightNavBar()) {
				siteElement = loadTopRightNavBar(hp, clazz);			
			}else {
				System.out.println("ERROR: NOT IMPLEMENTED"); // TODO - remove or log
				LogFactory.getAppLog(SiteMapElementLoader.class).error("NOT IMPLEMENTED");			
			}
		}else {
			LogFactory.getAppLog(SiteMapElementLoader.class).error("Cannot get SiteMapElement for null nav path");
		}
		return siteElement;
	}
	
	private static void loadModuleIfRequired(ElementClass nodeClass, HomePage hp) {
		String reqModuleName = nodeClass.getModuleName();
		String actModuleName = hp.getModuleName();
		if(!reqModuleName.equalsIgnoreCase(actModuleName)) {
			hp.loadModule(reqModuleName);
		}
	}
	
	private static boolean isLeftMenu() {		
		return (packageName.equalsIgnoreCase("left_menu")) ? true : false;		 
	}
	private static SiteMapElement loadLeft(HomePage hp, Class<?> clazz) {
		Optional<ContainerAction> leftMenuItem = hp.loadLeftMenuItem(clazz); 
		if(leftMenuItem.isPresent()) {
			ContainerAction element = leftMenuItem.get();
			if(isSiteMapElement(element)) {
				return (SiteMapElement) element;
			}
		}
		return null;
	}
	private static boolean isSiteMapElement(ContainerAction element) {
		return (element instanceof SiteMapElement) ? true : false;
	}
	private static boolean isTopRightNavBar() {
		if(parts != null) {
			return (parts[0].equals("top_right_nav_bar")) ? true : false;
		}else {
			return false;
		} 
	}
	private static SiteMapElement loadTopRightNavBar(HomePage hp, Class<?> clazz) {
		// TODO - Add top-right nav-bar.
		System.out.println("ERROR: NOT IMPLEMENTED FOR TOP-RGHT NAV-BAR"); // TODO - remove or log
		LogFactory.getAppLog(SiteMapElementLoader.class).error("NOT IMPLEMENTED FOR TOP-RGHT NAV-BAR");
		return null;
	}
}
