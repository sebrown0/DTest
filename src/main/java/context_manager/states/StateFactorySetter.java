/**
 * 
 */
package context_manager.states;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.helpers.IFrame;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public interface StateFactorySetter {
	ContextManager getContextManager();
	WebDriver getWebDriver();
	JsPanelHeaderBar setJsPanelHeaderBar();
	IFrame getIFrame();	
}
