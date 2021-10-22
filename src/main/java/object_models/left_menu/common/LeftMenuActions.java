/**
 * 
 */
package object_models.left_menu.common;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.StateLeftMenu;
import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;

/**
 * @author Steve Brown
 *
 * Actions that can be performed on a LeftMenu object.
 */
public class LeftMenuActions {
	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private ContextManager contextManager;	
	private Map<String, WebElement> anchors;
	private LeftMenuMapper menuMapper;	
	
	public LeftMenuActions(WebDriver driver, ContextManager contextManager, Map<String, WebElement> anchors, LeftMenuMapper menuMapper) {
		this.driver = driver;
		this.contextManager = contextManager;
		this.anchors = anchors;
		this.menuMapper = menuMapper;
	}

	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {
		ClassFieldGetter fIeldGetter = new ClassFieldGetter(clazz);		
		Optional<ContainerAction> item = null;
		Optional<String> prntName = fIeldGetter.getParentName();
		Optional<String> menuItem = fIeldGetter.getMenuItemName();
		
		contextManager.switchToStateInCurrentContext(StateLeftMenu.class);
		
		if(isChildMenuItem(prntName, menuItem)) {		
			item = clickParent(prntName.get()).loadElement(fIeldGetter);			
		}else if (isParentMenuItem(prntName)) {
			item = loadElement(fIeldGetter);
		}		
		return item;
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
		
	public Optional<ContainerAction> loadElement(ClassFieldGetter fieldGetter) {
		Optional<ContainerAction> element = Optional.empty();		
		Optional<String> elementName = fieldGetter.getMenuItemName();
		Optional<String> elementId = fieldGetter.getPanelTitle();
				
		if(elementName.isPresent() && elementId.isPresent()) {
			String name = elementName.get();
			String id = elementId.get();
			WebElement e =  anchors.get(name);
			
			contextManager.switchToFirstStateInCurrentContext();		
			logger.info("Loading [" + name + "]");
			
			try {
				e.click();				
				Optional<ContextState> cs = contextManager.findContext(id);				
				if(cs.isPresent()) {
					logger.debug("[" + name + "] already exists. Switching to that context and retrieving container");					
					ContainerAction el = cs.get().getContinerAction();				
					element = Optional.of(el);
				}else {
					element = Optional.of(getElement(name));
					logger.debug("[" + element.get().toString() + "] does not exist. Creating now");
				}
											
			} catch (Exception ex) {
				logger.error("Could not get menu element [" + name + "] [" + ex.getMessage() + "]");				 	
			}	
		}else {
			logger.error("Could not get menu element.");
		}
		return element;
	}
	
	public LeftMenuActions clickParent(String prntName) {
		contextManager.switchToStateInCurrentContext(StateLeftMenu.class);		
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
	
	private ContainerAction getElement(String elementName) {
		return ElementFactory.getElement(elementName, driver, contextManager);		
	}
}
