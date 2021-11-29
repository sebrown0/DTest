/**
 * 
 */
package object_models.left_menu.common;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.ContextState;
import factories.MenuElementFactory;
import factories.PayrollLeftMenuElementFactory;
import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Actions that can be performed on a LeftMenu object.
 */
public class LeftMenuActions {
	private Logger logger;
	private ContextManager contextManager;	
	private Map<String, WebElement> anchors;
	private LeftMenuMapper menuMapper;	
	private MenuContextChecker menuContextChecker;
	private CoreData hp;
	
	public LeftMenuActions(CoreData hp, Map<String, WebElement> anchors, LeftMenuMapper menuMapper) {
		this.hp = hp;
		this.logger = hp.getLogger();
		this.contextManager = hp.getContextManager();
		this.anchors = anchors;
		this.menuMapper = menuMapper;
		this.menuContextChecker = new MenuContextChecker(contextManager);
	}
	
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {		
		contextManager.switchToLeftMenu();		// Puts the context @ Module.StateLeftMenu
		ClassFieldGetter fieldGetter = new ClassFieldGetter(clazz);
		return getMenuItemAsContainer(fieldGetter);
	}

	private Optional<ContainerAction> getMenuItemAsContainer(ClassFieldGetter fieldGetter) {
		Optional<String> prntName = fieldGetter.getParentName();
		Optional<String> menuItem = fieldGetter.getMenuItemName();
		
		if(isChildMenuItem(prntName, menuItem)) {		
			return clickParent(prntName.get()).loadElement(fieldGetter);			
		}else if (isParentMenuItem(prntName)) {
			return loadElement(fieldGetter);
		}
		return Optional.empty();
	}

	private boolean isChildMenuItem(Optional<String> prntName, Optional<String> menuIem) {
		boolean retVal = false;
		
		if(prntName.isPresent()) {
			if(prntName.get().length() > 1 && menuIem.isPresent()) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	private boolean isParentMenuItem(Optional<String> prntName) {
		boolean retVal = false;
		
		if(prntName.isPresent()) {
			String name = prntName.get();
			if(name.equals("") || name.length() < 1) {
				retVal = true;
			}
		}
		return retVal;
	}
		
	/*
	 * Returns either an existing or new ContainerAction.
	 * ContainerAction is a JsPanel, Form etc that has ContextState in the form of: myContext.	 * 
	 */
	private Optional<ContainerAction> loadElement(ClassFieldGetter fieldGetter) {
		Optional<ContainerAction> elementContainer = Optional.empty();		
		Optional<String> elementName = fieldGetter.getMenuItemName();
		Optional<String> elementId = fieldGetter.getPanelTitle();
				
		if(elementName.isPresent() && elementId.isPresent()) {
			String name = elementName.get();
			String id = elementId.get();
			WebElement e =  anchors.get(name);
					
			logger.info("Loading [" + name + "]");
			
			try {
				e.click();				
				Optional<ContextState> cs = contextManager.findContext(id);				
				if(menuContextChecker.isExistingContext(cs)) {
					elementContainer = menuContextChecker.getExistingContainerFromContext(name, cs.get());
					menuContextChecker.setExistingAsCurrent(elementContainer);					
				}else { 	
//					e.click(); // didn;t work here
					elementContainer = Optional.of(getNewElementContainer(name));
					logger.debug("[" + elementContainer.get().toString() + "] does not exist. Creating now");
				}
											
			} catch (Exception ex) {
				logger.error("Could not get menu element [" + name + "] [" + ex.getMessage() + "]");				 	
			}	
		}else {
			logger.error("Could not get menu element.");
		}
		return elementContainer; 
	}
		
	public LeftMenuActions clickParent(String prntName) {
		contextManager.switchToLeftMenu();		
		WebElement activeMenuItem = getActiveMenuItem();
		if(activeMenuItem != null) {
			String currentlyActive = activeMenuItem.getText().trim();			
			if(!currentlyActive.equalsIgnoreCase(prntName)) {
				anchors.get(prntName).click();
			}	
		}	else {
			anchors.get(prntName).click();
		}
		return this;
	}
	
	public WebElement getActiveMenuItem() {
		WebElement activeMenuItem = null;
		try {
			activeMenuItem = menuMapper
					.getMenuElement()
					.findElement(By.cssSelector("a[class='dcjq-parent active']"));
		} catch (NoSuchSessionException e) {
			logger.error("No session found. Driver has probably been closed [" + e.getMessage() + "]"); 	
		} catch (Exception e) {
			logger.error("Failed to find active menu element [" + e.getMessage() + "]");
		}
		return activeMenuItem;
	}
	
	private ContainerAction getNewElementContainer(String elementName) {
		MenuElementFactory elementFactory = new PayrollLeftMenuElementFactory(hp); 
		return elementFactory.getElementForName(elementName);		
	}
}
