/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_results.DynamicTestSuiteData;
import library.common.controls.data.ControlTestData;
import library.common.controls.interfaces.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateFaFaCheck extends TestElementCreator  {
		
	public CreateFaFaCheck(
		TestElementDetails testElementDetails, XmlInfo testInfo, 
		DynamicTestSuiteData testData, String textExpected) {
		
		super(testElementDetails, testInfo, testData, textExpected);
		
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
