/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Create (add to list) test for control.
 * The test's message & execution is got
 * from the ElementTestCreator, i.e. CreateToolTipCheck.
 *  
 */
public class TestCreator {
	protected ControlFinder cntrlFinder;
	protected List<DynamicTest> testList;
	protected Optional<Control> cntrl;	
	protected ControlTest controlTest;
	
	public TestCreator( 
		ControlFinder cntrlFinder, 
		List<DynamicTest> testList, ControlTest controlTest) {
		
		this.cntrlFinder = cntrlFinder;
		this.testList = testList;
		this.controlTest = controlTest;
	}
	
	public void createTest(TestElementCreator elementTestCreator, List<DynamicTest> testList) {
		testList.add(
		DynamicTest.dynamicTest(
				elementTestCreator.getMessage(), 
			() ->	{ 
				getControlAndParent();
				elementTestCreator.createTestExecutor(cntrl); 
			}));			
	}

	protected void getControlAndParent() {
		this.cntrl = cntrlFinder.loadControl().getControl();
		this.controlTest = cntrlFinder.getControlsClass();
	}

//	public void setCntrlFinder(ControlFinder cntrlFinder) {
//		this.cntrlFinder = cntrlFinder;
//	}
//
//	public void setTestList(List<DynamicTest> testList) {
//		this.testList = testList;
//	}
//
//	public void setControlTest(ControlTest controlTest) {
//		this.controlTest = controlTest;
//	}	

}
