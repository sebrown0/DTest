/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextLoader;
import context_manager.ContextManager;
import context_manager.ContextState;
import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Actions that can be performed on a TopRightNavBar object.
 */
public class TopRightNavBarActions {
	private Logger logger = LogManager.getLogger();
	private WebDriver driver;
	private ContextManager contextManager;	
	private TopRightNavBarElements navBarElements;
	
	public TopRightNavBarActions(WebDriver driver, ContextManager contextManager, TopRightNavBarElements navBarElements) {		
		this.driver = driver;
		this.contextManager = contextManager;
		this.navBarElements = navBarElements;
	}
	
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {		
		contextManager.switchToTopRightNavBar();
		ClassFieldGetter fieldGetter = new ClassFieldGetter(clazz);
		return loadElement(fieldGetter);
	}

	/*
	 * Returns either an existing or new ContainerAction.
	 * ContainerAction is a JsPanel, Form etc that has ContextState in the form of: myContext.
	 */
	private Optional<ContainerAction> loadElement(ClassFieldGetter fieldGetter) {
		Optional<ContainerAction> elementContainer = Optional.empty();		
		Optional<String> elementName = fieldGetter.getOriginalName();
				
		if(elementName.isPresent()) {
			String name = elementName.get();
			NavBarElement e =  navBarElements.getElement(elementName.get());
					
			logger.info("Loading [" + name + "]");
			
			try {								
				Optional<ContextState> cs = contextManager.findContext(e);				
				if(isExistingContext(cs)) {
					elementContainer = getExistingContainerFromContext(name, cs.get());
					setExistingAsCurrent(elementContainer);					
				}else {					
					elementContainer = Optional.of((ContainerAction) e.clickElement());
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
	
	private boolean isExistingContext(Optional<ContextState> cs) {
		if(cs != null) {
			return cs.isPresent();	
		}else {
			return false;
		}		
	}
	private Optional<ContainerAction> getExistingContainerFromContext(String name, ContextState cs) {
		logger.debug("[" + name + "] already exists. Switching to that context and retrieving container");					
		ContainerAction el = cs.getContinerAction();				
		return Optional.of(el);
	}
	private void setExistingAsCurrent(Optional<ContainerAction> elementContainer) {
		elementContainer.ifPresent(e -> {
			ContextLoader loader = new ContextLoader(contextManager);
			loader.setContainerItemAsCurrentContext(elementContainer);	
		});
		
	}
		
//	private ContainerAction getNewElementContainer(String elementName) {
//		PayrollTopRightNavBarElementFactory elementFactory = new PayrollTopRightNavBarElementFactory(driver, contextManager);
//		return elementFactory.getElementForName(elementName);		
//	}
}
