/**
 * 
 */
package object_models.panels;

import java.util.Optional;

import context_manager.ContextManager;
import context_manager.ContextState;
import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class PanelSwitcher <T extends JsPanel> {
	/*
	 * 1. Get current context
	 * 2. If it's JsPanel can switch, else TODO
	 * 3. If current is panel we're looking for
	 * 4. We're done, else
	 * 5. Use current panel to dropdown menu, and
	 * 6. 	click panel wr're looking for.
	 * 7. Get context of panel we;re looking for.
	 * 8. Set context manager to context we're looking for.
	 *  
	 */
	
	private Class<T> panelToSwitchTo;
	private ContextManager manager;
	private ContextState currentContext;
	private Optional<ContextState> switchToPanelContext;
	private Optional<String> switchToPanelTitle;
	private boolean foundContext = false;
	private String panelId;
	
	public PanelSwitcher(Class<T> panelToSwitchTo, ContextManager manager) {
		this.panelToSwitchTo = panelToSwitchTo;
		this.manager = manager;
	}

	public void switchToExistingPanel() {
		currentContext = manager.getCurrentContext();
		setContextForSwitchToPanel();
		switchToPanelContext.ifPresentOrElse(
				c -> {
					ContainerAction container = currentContext.getContinerAction();
					if(currentContext.equals(c)) {
						// 4. we're done, panel is current
					}else if(foundContext && container instanceof JsPanel) {
						if(switchToPanelFrom((JsPanel) container) == true) {
							manager
								.moveToExistingContext(c)
								.switchToDefaultStateInCurrentContext();
						}
					}
				}, 
				new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub					
						System.out.println("PanelSwitcher.switchToExistingPanel: could not switch to panel."); // TODO - remove or log
						// Go to first context????
					}
				});		
	}
	private void setContextForSwitchToPanel() {
		ClassFieldGetter fieldGetter = new ClassFieldGetter(panelToSwitchTo);
		foundContext = false;
		switchToPanelTitle = fieldGetter.getPanelTitle();
		switchToPanelTitle.ifPresent(t -> {				
				switchToPanelContext = manager.findContext(t);		
				switchToPanelContext.ifPresent(c -> {
					panelId = c.getContextId().getActualId();
					foundContext = true;
				});
		});	
	}
	
	private boolean switchToPanelFrom(JsPanel currPanel) {
		return 
		  currPanel
				.getHeaderBar()
				.getToolBar()
				.switchToPanelFromPanel(panelId, currPanel);
	}
		
}
