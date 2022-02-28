/**
 * 
 */
package control_builder.control_getters.group;

import controls.Control;
import controls.ControlGroup;
import controls.adders.RowAdder;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterRow extends ControlGetterGroup {
	
	
	public ControlGetterRow(String name, CoreData coreData) {
		super(name, coreData);				
	}
	
	@Override
	public Control getControl() {
		return 
			new ControlGroup()
				.addElements(cntrls, new RowAdder());
	}
	
}
