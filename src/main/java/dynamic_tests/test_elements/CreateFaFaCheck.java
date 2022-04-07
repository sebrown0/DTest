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
public class CreateFaFaCheck extends TestElementCreator  {
		
	public CreateFaFaCheck(
		TestElementDetails testElementDetails, XmlInfo testInfo, DynamicTestSuiteData testData, 
		ControlTest controlTest, String textExpected) {
		
		super(testElementDetails, testInfo, testData, controlTest, textExpected);
		
	}
	
	@Override
	protected void executeTest(Optional<Control> cntrl) {
		String faFaActual = ControlTestData.getFaFaText(cntrl);	
		
		assertEquals.assertTextEquals(
				testElementDetails, 
				new TestElementData().setTextExpected(textExpected).setTextActual(faFaActual), 
				cntrl);		
	}

	@Override
	protected String getMessage() {
		return "Is [" + testElementDetails.getName() +"] FaFa correct?";
	}
	
	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("FaFaCheck");		
	}
}
