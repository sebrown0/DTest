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

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckString extends TestCreator {
	private String textExpected;
	private XmlInfo testInfo;
	
	public CreateTextCheckString(XmlInfo testInfo,
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String textExpected) {
		
		super(cntrlFinder, testList, controlTest);

		this.testInfo = testInfo;
		this.textExpected = textExpected;	
	}
	
	@Override
	public void createTest(String elName) {
		testList.add(
		DynamicTest.dynamicTest(
			"Is [" + elName +"] text correct?", 
			() -> {
				getControlAndParent();
				
				//Has side effect -> the test result in testResultData is set according to the test result. 
				new 
					AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl)
						.assertTextEquals(textExpected);
				
				
//				result.addTestData(null);
			}));		
	}
		
}
