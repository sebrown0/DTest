/**
 * 
 */
package site_mapper;

import java.util.regex.Pattern;

import logging.LogFactory;
import object_models.forms.ContainerAction;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public class NavFactory {
	public static SiteMapElement getSiteMapElement(String navPath, HomePage hp, Class<?> clazz) {
		SiteMapElement siteElement = null;
		
		if(navPath != null){
			String[] parts = navPath.split(Pattern.quote("."));
			if(parts != null) {
				String menu = parts[0];
				if(menu.equalsIgnoreCase("left_menu")) {
					if(hp.loadLeftMenuItem(clazz).isPresent()) {
						ContainerAction containerAction = hp.loadLeftMenuItem(clazz).get();
						if(containerAction instanceof SiteMapElement) {
							siteElement = (SiteMapElement) containerAction;
						}
					}		
				}else {
					// TODO - Add top-right nav-bar.
					System.out.println("ERROR: NOT IMPLEMENTED FOR TOP-RGHT NAV-BAR"); // TODO - remove or log
					LogFactory.getAppLog(NavFactory.class).error("NOT IMPLEMENTED FOR TOP-RGHT NAV-BAR");
				}
			}
		}else {
			LogFactory.getAppLog(NavFactory.class).error("Cannot get SiteMapElement for null nav path");
		}
		return siteElement;
	}
}
