/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_results.DynamicTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckString extends TestCreator {
	private String textExpected;
	private AssertTextEquals assertEquals;
	private DynamicTestData testData;

	public CreateTextCheckString(
		XmlInfo testInfo, DynamicTestData testData,
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String textExpected) {
		
		super(testInfo, cntrlFinder, testList, controlTest);

		this.textExpected = textExpected;	
	}
	
	@Override
	public void createTest(String elName) {
		testList.add(
		DynamicTest.dynamicTest(
			"Is [" + elName +"] text correct?", 
			() ->	executeTest()));			
	}
		
	private void executeTest() {
		getControlAndParent();				
		assertEquals = 
				new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl);
		assertEquals.assertTextEquals(textExpected);		
	}
	
}
