/**
 * 
 */
package control_builder.control_getters;

import java.util.List;

import org.openqa.selenium.By;

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
public final class ControlGetterTab extends ControlGetterGroup {
	private List<ControlGetter> cntrls;
	private String name;
	private By findBy;
	
	public ControlGetterTab(String name, CoreData coreData, By findBy) {
		super(name, coreData);
		
		this.name = name;
		this.findBy = findBy;	
	}

	@Override
	public ControlGetterGroup addControls(List<ControlGetter> cntrls) {
		this.cntrls = cntrls;
		return this;
	}
	
	@Override
	public Control getControl() {
		return new Tab(name, driver, findBy).addElements(cntrls);
	}
	
}
