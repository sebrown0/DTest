/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

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

public class CreateTextCheckString implements ElementTestCreator {
	private AssertTextEquals assertEquals;
	
	private String textExpected;	
	private DynamicTestData testData;	
	private XmlInfo testInfo;	
	private ControlTest controlTest;
	
	public CreateTextCheckString(
		XmlInfo testInfo, DynamicTestData testData, 
		ControlTest controlTest, String textExpected) {

		this.testInfo = testInfo;
		this.testData = testData;
		this.textExpected = textExpected;
		this.controlTest = controlTest;
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		assertEquals = 
				new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, testData, cntrl);
		assertEquals.assertTextEquals(textExpected);		
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] text correct?";
	}

}
