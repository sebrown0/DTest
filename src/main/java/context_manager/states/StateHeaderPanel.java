/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import context_manager.ContextManager;
import context_manager.ContextState;
import exceptions.PanelException;
import object_models.helpers.ClassFieldGetter;
import object_models.helpers.IFrame;
import object_models.panels.JsPanel;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderPanel extends State {
	private JsPanelHeaderBar bar;
	private IFrame iFrame;
	private ContextManager manager;
	
	public StateHeaderPanel(ContextManager manager, JsPanelHeaderBar bar, IFrame iFrame) {
		super(manager.getCurrentContext());
		this.manager = manager;
		this.bar = bar;
		this.iFrame = iFrame;
	}
	
	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
		ClassFieldGetter fieldGetter = new ClassFieldGetter(panel);		
		Optional<String> panelTitle = fieldGetter.getPanelTitle();
		
		panelTitle.ifPresent(title -> {			
			Optional<ContextState> csCurr = manager.findContext(title);
			csCurr.ifPresentOrElse(cs -> {
				manager.moveToStateInCurrentContext(this); 		
				bar.getToolBar().switchToPanel(cs.getContextId().getActualId());
				logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
			}, 
			new PanelException("Could not switch to panel [" + panel + "]"));
			
			
			
//			if(csCurr.isPresent()) {				
//				ContextState cs = csCurr.get();				
//				manager.moveToStateInCurrentContext(this); 		
//				bar.getToolBar().switchToPanel(cs.getContextId().getActualId());
//				logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
//			}else {
//				logger.error("Could not switch to panel [" + panel + "]"); 	
//			}
		});		
	}
	
	@Override
	public Optional<State> getNext() {		
		return Optional.of(new StateIframe(super.context, iFrame));
	}
	
	@Override
	public void close() {
		logger.debug("Closing state [" + this + "]");
		bar.getControlBar().clickClose();		
	}

	@Override
	public void switchToMe() {
		context.switchToDefaultContent();
		System.out.println("StateHeaderPanel->switchToMe"); // TODO - remove or log 	
//		logger.error("switchToMe not implemented!");
	}

	@Override
	public boolean isContextCloser() {
		return true;
	}

	@Override
	public boolean isDefaultState() {
		return true;
	}
}
