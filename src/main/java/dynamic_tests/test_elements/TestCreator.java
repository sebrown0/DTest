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
 */
public class TestCreator {
	protected ControlFinder cntrlFinder;
//	protected XmlInfo testInfo;
	protected List<DynamicTest> testList;
	protected Optional<Control> cntrl;	
	protected ControlTest controlTest;
	
	public TestCreator(
//		XmlInfo testInfo, 
		ControlFinder cntrlFinder, 
		List<DynamicTest> testList, ControlTest controlTest) {
		
//		this.testInfo = testInfo;
		this.cntrlFinder = cntrlFinder;
		this.testList = testList;
		this.controlTest = controlTest;
	}
	
	public void createTest(ElementTestCreator elementTestCreator, List<DynamicTest> testList) {
		testList.add(
		DynamicTest.dynamicTest(
				elementTestCreator.getMessage(), 
			() ->	{ 
				getControlAndParent();
				elementTestCreator.executeTest(cntrl); 
			}));			
	}

	protected void getControlAndParent() {
		this.cntrl = cntrlFinder.loadControl().getControl();
		this.controlTest = cntrlFinder.getControlsClass();
	}	
	
//	public void createTestX(String elName, ElementTestCreator creator) {
//		creator.createTest(elName, testList);
//	}
//	
//	private void executeTest() {
//		getControlAndParent();				
//		assertEquals = 
//				new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl);
//		assertEquals.assertTextEquals(textExpected);		
//	}
	
	
//	public abstract void createTest(String elName);
}
