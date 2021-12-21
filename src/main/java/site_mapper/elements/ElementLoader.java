/**
 * 
 */
package site_mapper.elements;

import java.util.Optional;

import controls.ControlTest;
import object_models.pages.homepage.HomePage;
import site_mapper.NodeClass;
import site_mapper.SiteMapElement;
import site_mapper.SiteMapElementLoader;
import site_mapper.class_finder.ClassFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementLoader {
	private Optional<SiteMapElement> container;
	private NodeClass nodeClass;
	private HomePage homePage;
	
	public ElementLoader(NodeClass nodeClass, HomePage homePage) {
		this.nodeClass = nodeClass;
		this.homePage = homePage;
	}

	/*
	 * TODO - Load the container for the Node & not every element.
	 */
	public ControlTest getControlTest() {
		loadContainerIfNull();		
		if(container.isPresent()) {
			SiteMapElement e = container.get();
			if(e instanceof ControlTest) {
				return (ControlTest) e;
			}else {
				System.out.println("***** getControlTest() 1 *****"); // TODO - remove or log
			}
		}else {
			System.out.println("***** getControlTest() 2 *****"); // TODO - remove or log 	
			// TODO - THROW ERROR??
		}
		return null;
	}
	
	private void loadContainerIfNull() {
		if(container == null) { loadContainer(); }
	}	
	private void loadContainer() {
		container = getNodeAsSiteMapElement();
	}
	public Optional<SiteMapElement> getNodeAsSiteMapElement() {		
		Class<?> clazz = getClazz();		
		return 
			Optional.ofNullable(
				SiteMapElementLoader.getAndLoadSiteMapElement(nodeClass, homePage, clazz));		
	}
	public Class<?> getClazz(){		
		return ClassFinder.getClazz(nodeClass);
	}
	
}
