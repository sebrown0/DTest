/**
 * 
 */
package object_models.left_menu.common;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateLeftMenu;
import control_mapping.MenuMap;
import exceptions.StaleAnchorException;
import object_models.forms.ContainerAction;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class LeftMenu implements CallingState {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	private Logger logger;
	private LeftMenuElements elements;	
	private LeftMenuMapper menuMapper;	
	private ContextManager contextManager;
	private LeftMenuActions menuActions;
	
	public LeftMenu(CoreData hp) {
		this.driver = hp.getWebDriver();
		this.contextManager = hp.getContextManager();
		this.logger = hp.getLogger();
		this.contextManager.setLatestCallingState(this);		
		
		mapAnchors();
		menuActions = new LeftMenuActions(hp, anchors, menuMapper);
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
		Optional<ContainerAction> action = menuActions.clickAndLoad(clazz);
		return action;		
	}
	
	public LeftMenuActions clickParent(String prntName) throws StaleAnchorException {
		try {
			LeftMenuActions action = menuActions.clickParent(prntName); 
			return action;	
		} catch (StaleElementReferenceException e) {
			System.out.println("caught 1- reload" ); // TODO - remove or log 	
			mapAnchors();
			throw new StaleAnchorException("Left menu anchors are stale");
		} catch (Exception e) {
			System.out.println("caught 2- reload" ); // TODO - remove or log 	
			return null;
		}
		
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
		return new StateLeftMenu(context, driver);
	}	
}
