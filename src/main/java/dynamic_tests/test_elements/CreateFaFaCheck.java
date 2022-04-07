/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_results.DynamicTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateFaFaCheck extends TestElementCreator  {
	private AssertTextEquals assertEquals;
	
	public CreateFaFaCheck(
		String elmntName, XmlInfo testInfo, DynamicTestData testData, 
		ControlTest controlTest, String textExpected) {
		
		super(elmntName, testInfo, testData, controlTest, textExpected);
		
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		String faFaActual = ControlTestData.getFaFaText(cntrl);		
		assertEquals = 
			new AssertTextEquals(testInfo, controlTest, testData, cntrl);
		assertEquals.assertTextEquals(elmntName, "FaFaCheck", textExpected, faFaActual);		
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] FaFa correct?";
	}
	
}
