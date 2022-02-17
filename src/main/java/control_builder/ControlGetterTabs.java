/**
 * 
 */
package control_builder;

import controls.ControlGroup;
import controls.TabGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterTabs extends GroupControlGetter {
	private ControlGroup grp;
	
	public ControlGetterTabs(CoreData coreData, TabGroup grp) {
		super(grp.getCoreData(), grp.getFindBy());
		
		this.grp = grp;
	}

	@Override
	public ControlGroup getControl() {
		return grp;
	}

}
