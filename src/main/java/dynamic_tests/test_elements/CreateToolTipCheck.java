/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateToolTipCheck extends TestElementCreator {	

	public CreateToolTipCheck(
		TestElementDetails testElementDetails, XmlInfo testInfo, DynamicTestSuiteData testData, 
		ControlTest controlTest, String textExpected) {
		
		super(testElementDetails, testInfo, testData, controlTest, textExpected);
		
	}
	
	@Override
	protected void executeTest(Optional<Control> cntrl) {
		String toolTipTextActual = ControlTestData.getControlToolTip(cntrl);
		assertEquals.assertTextEquals(
			testElementDetails, 
			new TestElementData().setTextExpected(textExpected).setTextActual(toolTipTextActual), 
			cntrl);
	}

	@Override
	protected String getMessage() {
		return "Is [" + testElementDetails.getName() +"] tool tip correct?";
	}
	
	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("ToolTipCheck");		
	}
}
