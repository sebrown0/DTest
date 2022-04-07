/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_results.DynamicTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckTestData implements ElementTestCreator {
	private TestAdderWithData testAdderWithData;		
	private DynamicTestData testData;	
	@SuppressWarnings("unused")
	private XmlInfo testInfo;	
	private ControlTest controlTest;
	
	public CreateTextCheckTestData(
		XmlInfo testInfo, DynamicTestData testData, 
		ControlTest controlTest, TestAdderWithData testAdderWithData) {
	
		this.testInfo = testInfo;
		this.testData = testData;		
		this.controlTest = controlTest;
		this.testAdderWithData = testAdderWithData;
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		TestDataInserter.insertAnyTestData(testAdderWithData, controlTest);
		new 
			AssertTextEquals(null, controlTest, testData, cntrl)
				.assertTextEquals(testAdderWithData);
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] text correct?";
	}
	
//	@Override
//	public void createTest(String elName) {
//		testList.add(
//			DynamicTest.dynamicTest(
//				"Is [" + elName +"] text correct?", 
//				() -> {
//					getControlAndParent();
//					TestDataInserter.insertAnyTestData(testData, controlTest);
////					DynamicTestData testResultData = new DynamicTestData();
//					new 
//						AssertTextEquals(null, controlTest, cntrl)
//							.assertTextEquals(testData); 	
//				}));	
//	}
		
}
