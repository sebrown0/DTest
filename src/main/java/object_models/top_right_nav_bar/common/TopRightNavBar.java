/**
 * 
 */
package object_models.top_right_nav_bar.common;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import context_manager.CallingState;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.State;
import context_manager.states.StateTopRightNavBar;
import object_models.forms.ContainerAction;
import object_models.helpers.title.WebElementTitleChecker;
import object_models.modules.common.nav.NavBarElement;
import object_models.modules.common.nav.nav_bar_elements.NavBarElementGetter;
import object_models.modules.common.nav.nav_bar_elements.NavBarElementStrategy;
import object_models.modules.common.nav.quick_links.QuickLinks;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class TopRightNavBar implements ElementChecker, NavBarElementGetter, CallingState {
	private WebDriver driver;
	private TopRightNavBarElements navBarElements; 
	private QuickLinks quickLinks;
	private ContextManager contextManager;
	
	public TopRightNavBar(WebDriver driver, ContextManager contextManager) {
		this.driver = driver;
		this.contextManager = contextManager;
		this.contextManager.setLatestCallingState(this);		
	}
	
	public void loadElements(NavBarElementStrategy elementStrategy) {
		navBarElements = new TopRightNavBarElements(driver, elementStrategy);
		quickLinks = elementStrategy.getQuickLinks();
	}
			
	@Override
	public boolean checkElementTitles() {
		return WebElementTitleChecker.allTitlesPresentAndCorrect(navBarElements.getNavElements(), navBarElements.getNavBarElementTitles());
	}
	
	/*
	 * Getters below
	 */
	public TopRightNavBarElements getNavBarElements() {
		return navBarElements.locateNavBar();
	}
	public QuickLinks getQuickLinks() {
		return quickLinks;
	}		
	
	@Override // NavBarElementGetter
	public Optional<ContainerAction> clickAndLoad(Class<?> clazz) {		
		TopRightNavBarLoader actions = new TopRightNavBarLoader(contextManager, navBarElements);
		return actions.clickAndLoad(clazz);
	}

	@Override // NavBarElementGetter
	public Optional<NavBarElement> getNavBarElement(String elementName) {		
		return Optional.ofNullable(navBarElements.getElement(elementName));
	}

	@Override
	public State getState(ContextState context) { 
		return new StateTopRightNavBar(context, driver);
	}	
}
