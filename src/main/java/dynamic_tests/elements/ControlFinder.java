/**
 * 
 */
package dynamic_tests.elements;

import java.util.List;
import java.util.Optional;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.mappers.TestNode;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlFinder {
	private String name;
	private TestNode testNode;
	private MenuItem item;
	private HomePage hp;	
	private ControlTest controlTest;
	private Optional<Control> cntrl = null;
	private ControlTest cntrlTest;
	
	public ControlFinder(TestNode testNode, HomePage hp, MenuItem item, String name) {		
		this.testNode = testNode;
		this.hp = hp;
		this.item = item;
		this.name = name;
	}

	public ControlFinder loadControl() {
		List<String> prntNames = testNode.getPrntNames();
		
		cntrlTest = loadTestsContainerAndGetAsControlTest();

		//should always be at least one name (the element's node)
		if(prntNames != null) {
			int idx = 0;
			String prntName = prntNames.get(idx);
			ControlGroup grp = (ControlGroup) cntrlTest.getControl(prntName).get();
			while(prntName != null && idx < prntNames.size()-1) {				
				idx++;
				prntName = prntNames.get(idx);
				grp = (ControlGroup) grp.getControlByTitle(prntName).get();				
			}
			cntrl = grp.getControlByTitle(name);			
		}	
		return this;
	}
	public Optional<Control> getControl() {
		return cntrl;
	}
	public ControlTest getControlsClass() {
		return cntrlTest;
	}

	private ControlTest loadTestsContainerAndGetAsControlTest() {		
		if(controlTest == null) {
			controlTest = getControlTest();
		}
		return controlTest;
	}
	
	private ControlTest getControlTest() {
		return ElementLoader.getControlTest(item, hp);
	}
	
}
