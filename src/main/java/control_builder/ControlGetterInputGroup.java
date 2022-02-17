/**
 * 
 */
package control_builder;

import org.openqa.selenium.WebElement;

import controls.Control;
import controls.InputGroup;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class ControlGetterInputGroup extends ControlGetter {
	private Control grp;
	
	public ControlGetterInputGroup(CoreData coreData, InputGroup grp) {
		super(grp.getCoreData(), grp.getFindBy());
		
		this.grp = grp;
	}

	@Override
	public Control getControl() {
		return grp;
	}
	@Override
	public ControlGetter setElement(WebElement el) {
		System.out.println("ControlGetterInputGroup.setElement ** NOT IMPLEMENTED **"); // TODO - Implement 	
		return null;
	}
}
