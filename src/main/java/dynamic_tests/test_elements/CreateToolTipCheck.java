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
public class CreateToolTipCheck implements ElementTestCreator {
	private String toolTipTextExpected;
	private AssertTextEquals assertEquals;
	
	private DynamicTestData testData;	
	private XmlInfo testInfo;	
	private ControlTest controlTest;
	
	public CreateToolTipCheck(
			XmlInfo testInfo, DynamicTestData testData, 
			ControlTest controlTest, String toolTipText) {
		
		this.testInfo = testInfo;
		this.testData = testData;
		this.controlTest = controlTest;
		this.toolTipTextExpected = toolTipText;
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		String toolTipTextActual = ControlTestData.getControlToolTip(cntrl);
		assertEquals = 
				new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, testData, cntrl);
		assertEquals.assertTextEquals("ToolTipCheck", toolTipTextExpected, toolTipTextActual);		
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] tool tip correct?";
	}

}
