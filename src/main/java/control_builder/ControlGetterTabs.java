/**
 * 
 */
package control_builder;

import controls.Control;
import controls.TabGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterTabs extends ControlGetter {
	private Control grp;
	
	public ControlGetterTabs(CoreData coreData, TabGroup grp) {
		super(grp.getCoreData(), grp.getFindBy());
		
		this.grp = grp;
	}

	@Override
	public Control getControl() {
		return grp;
	}

}
