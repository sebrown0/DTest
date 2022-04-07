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
public class CreateFaFaCheck implements ElementTestCreator {
	private AssertTextEquals assertEquals;
	
	private String faFaExpected;
	private DynamicTestData testData;	
	private XmlInfo testInfo;	
	private ControlTest controlTest;
	
	public CreateFaFaCheck(
		XmlInfo testInfo, DynamicTestData testData, 
		ControlTest controlTest, String faFa) {
		
		this.testInfo = testInfo;
		this.testData = testData;
		this.faFaExpected = faFa;
		this.controlTest = controlTest;
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		String faFaActual = ControlTestData.getFaFaText(cntrl);		
		assertEquals = 
			new AssertTextEquals(
				testInfo.getTestReportStrategy(), controlTest, testData, cntrl);
		assertEquals.assertTextEquals("FaFaCheck", faFaExpected, faFaActual);		
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] FaFa correct?";
	}
	
}
