/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class TestCreator {
	protected ControlFinder cntrlFinder;
	protected XmlInfo testInfo;
	protected List<DynamicTest> testList;
	protected Optional<Control> cntrl;	
	protected ControlTest controlTest;
	
	public TestCreator(
		XmlInfo testInfo, ControlFinder cntrlFinder, 
		List<DynamicTest> testList, ControlTest controlTest) {
		
		this.testInfo = testInfo;
		this.cntrlFinder = cntrlFinder;
		this.testList = testList;
		this.controlTest = controlTest;
	}
	
	protected void getControlAndParent() {
		this.cntrl = cntrlFinder.loadControl().getControl();
		this.controlTest = cntrlFinder.getControlsClass();
	}
	
	public abstract void createTest(String elName);
}
