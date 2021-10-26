/**
 * 
 */
package object_models.panels;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 */
public interface ZZZ_PanelSwitcher {	
	<T extends JsPanel> void switchToExistingPanel(Class<T> panel);
	void switchToExistingPanel(JsPanel panel, ContextState cs);
}
