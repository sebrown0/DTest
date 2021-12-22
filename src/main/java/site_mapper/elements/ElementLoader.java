/**
 * 
 */
package site_mapper.elements;

import java.util.Optional;

import controls.ControlTest;
import object_models.pages.homepage.HomePage;
import site_mapper.NodeClass;
import site_mapper.SiteMapElement;
import site_mapper.class_finder.ClassFinder;
import site_mapper.jaxb.SiteMapElementLoader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Make static class.
 * @since 1.0
 * 
 * Get the node as a container, convert to ControlTest
 * and return it.
 */
public class ElementLoader {
	private static Optional<SiteMapElement> container;
	private static NodeClass nodeClass;
	private static HomePage homePage;
	
	public static ControlTest getControlTest(NodeClass nc, HomePage hp) {		
		setVariables(nc, hp);
		loadContainer();		
		return getContainerAsControlTest();
	}
	private static void setVariables(NodeClass nc, HomePage hp) {
		nodeClass = nc;
		homePage = hp;
	}
	private static void loadContainer() {
		container = getNodeAsSiteMapElement();
	}
	private static Optional<SiteMapElement> getNodeAsSiteMapElement() {		
		Class<?> clazz = getClazz();		
		return 
			Optional.ofNullable(
				SiteMapElementLoader.getAndLoadSiteMapElement(nodeClass, homePage, clazz));		
	}
	private static Class<?> getClazz(){		
		return ClassFinder.getClazz(nodeClass);
	}
	private static ControlTest getContainerAsControlTest() {
		ControlTest ct = null;
		if(container.isPresent()) {
			SiteMapElement e = container.get();
			if(e instanceof ControlTest) {
				ct = (ControlTest) e;
			}else {
				System.out.println("***** getControlTest() 1 *****"); // TODO - remove or log
			}
		}else {
			System.out.println("***** getControlTest() 2 *****"); // TODO - remove or log 	
			// TODO - THROW ERROR??
		}
		return ct;
	}
}
