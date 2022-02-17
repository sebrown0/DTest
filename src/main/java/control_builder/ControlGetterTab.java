/**
 * 
 */
package control_builder;

import controls.Control;
import controls.Tab;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterTab extends ControlGetter {
	private Control tab;
	
	public ControlGetterTab(CoreData coreData, Tab tab) {
		super(coreData);
		this.tab = tab;	
	}

	public String getName() {
		return ((Tab)tab).getName();
	}
	
	@Override
	public Control getControl() {
		return tab;
	}
	
}
