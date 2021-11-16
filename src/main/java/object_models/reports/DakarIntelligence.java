/**
 * 
 */
package object_models.reports;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class DakarIntelligence extends JsPanelWithIFrame {
	public static final String PANEL_TITLE = "Reports";

	public DakarIntelligence(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, contextManager);		
	}

	// Elements

	// Tabs

}
