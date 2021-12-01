/**
 * 
 */
package object_models.left_menu.common;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.StaleElementReferenceException;
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
	private CoreData coreData;
	
	public LeftMenuActions(CoreData coreData, Map<String, WebElement> anchors, LeftMenuMapper menuMapper) {
		this.coreData = coreData;
		this.logger = coreData.getLogger();
		this.contextManager = coreData.getContextManager();
		this.anchors = anchors;
		this.menuMapper = menuMapper;
		this.menuContextChecker = new MenuContextChecker(contextManager);
	}
	
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) throws Exception{		
		contextManager.switchToLeftMenu();		// Puts the context @ Module.StateLeftMenu
		ClassFieldGetter fieldGetter = new ClassFieldGetter(clazz);
		return getMenuItemAsContainer(fieldGetter);
	}

	private Optional<ContainerAction> getMenuItemAsContainer(ClassFieldGetter fieldGetter) throws Exception{
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
		
	public LeftMenuActions clickParent(String prntName) throws Exception, StaleElementReferenceException {
		contextManager.switchToLeftMenu();		
		WebElement activeMenuItem = getActiveMenuItem();
		if(activeMenuItem != null) {
			String currentlyActive = activeMenuItem.getText().trim();			
			if(!currentlyActive.equalsIgnoreCase(prntName)) {
//				clickAnchor(prntName,1);
				anchors.get(prntName).click();
			}	
		}	else {
//			clickAnchor(prntName,1);
			WebElement app = coreData.getWebDriver().findElement(By.cssSelector("div[class='app-body']"));
			WebElement nav = app.findElement(By.id("nav-accordion"));
			System.out.println("->" + nav.getAttribute("class")); // TODO - remove or log 	
			WebElement anc = anchors.get(prntName);
			System.out.println("->" + anc.toString()); // TODO - remove or log 	
			anc.click();
		}
		return this;
	}
	
//	private void clickAnchor(String prntName, int tryNum) throws StaleElementReferenceException {
//		final int MAX_TRIES = 5;
//		if(tryNum <= MAX_TRIES) {
//			try {
//				System.out.println("->" + coreData.getWebDriver().findElement(By.id("nav-accordion")).getAttribute("class")); // TODO - remove or log 	
//				WebElement anchor = anchors.get(prntName); 
//				anchor.click();
//			} catch (StaleElementReferenceException e) {
//				//Reload
//				menuMapper.map();
//				throw new StaleElementReferenceException("");
//			} catch (Exception e) {
//				System.out.println("->" + e); // TODO - remove or log
//			}	
//		}		
//	}
	
	public WebElement getActiveMenuItem() {
		WebElement activeMenuItem = null;
		try {
			activeMenuItem = menuMapper
					.getMenuElement()
					.findElement(By.cssSelector("a[class='dcjq-parent active']"));
		} catch (NoSuchSessionException e) {
			logger.error("No session found. Driver has probably been closed [" + e.getMessage() + "]"); 	
		} catch (Exception e) {
			logger.debug("No active menu element");
		}
		return activeMenuItem;
	}
	
	private ContainerAction getNewElementContainer(String elementName) {
		MenuElementFactory elementFactory = new PayrollLeftMenuElementFactory(coreData); 
		return elementFactory.getElementForName(elementName);		
	}
}
