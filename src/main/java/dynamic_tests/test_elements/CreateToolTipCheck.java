/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.data.ControlTestData;
import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateToolTipCheck extends TestCreator {
	private String toolTipTextExpected;
	private AssertTextEquals assertEquals;
	
	public CreateToolTipCheck(XmlInfo testInfo,
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String toolTipText) {
		super(testInfo, cntrlFinder, testList, controlTest);

		this.toolTipTextExpected = toolTipText;
	}
	
	@Override
	public void createTest(String elName) {		
		if(toolTipTextExpected != null && toolTipTextExpected.length()>0) {
			testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] tool tip correct?", 
					() ->	executeTest()));			
		}
	}
	
	private void executeTest() {
		getControlAndParent();
		String toolTipTextActual = ControlTestData.getControlToolTip(cntrl);
		assertEquals = new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl);
		assertEquals.assertTextEquals(toolTipTextExpected, toolTipTextActual);	
	}
		
}
