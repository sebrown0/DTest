/**
 * 
 */
package object_models.panels;

/**
 * @author Steve Brown
 *
 */
public interface PanelSwitcher {
	<T extends JsPanel> void switchToExistingPanel(Class<T> panel);
}
