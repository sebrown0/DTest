/**
 * 
 */
package object_models.left_menu.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateLeftMenu;
import controls.MenuMap;
import object_models.forms.ContainerAction;

/**
 * @author Steve Brown
 *
 */
public class LeftMenu implements CallingState {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	private Logger logger = LogManager.getLogger();
	private LeftMenuElements elements;	
	private LeftMenuMapper menuMapper;	
	private ContextManager contextManager;
	private LeftMenuActions menuActions;
	
	public LeftMenu(WebDriver driver, ContextManager contextManager) {
		this.driver = driver;
		this.contextManager = contextManager;
		this.contextManager.setCallingState(this);		
		
		mapAnchors();
		menuActions = new LeftMenuActions(driver, contextManager, anchors, menuMapper);
	}

	private void mapAnchors() {
		menuMapper = new LeftMenuMapper(driver);		
		try {
			this.anchors = new MenuMap(new LeftMenuFactory(driver)).getAnchors().get();
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Unable to get anchors from menu map");
		}
	}

	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {
		return menuActions.clickAndLoad(clazz); 
	}
	
	public LeftMenuActions clickParent(String prntName) {
		return menuActions.clickParent(prntName);
	}
		
	/*
	 *  Getters & Setters		
	 */
	public Map<String, Optional<List<String>>> getActualMenu(){
		return menuMapper.map().getMenu();		
	}
	
	public void setElements(LeftMenuElements elements) {
		this.elements = elements;		
	}
	
	public LeftMenuElements getElements() {
		return elements;
	}

	@Override
	public State getState(ContextState context) { 
		return new StateLeftMenu(contextManager, driver);
	}	
}
