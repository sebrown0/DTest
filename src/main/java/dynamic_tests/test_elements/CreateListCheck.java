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
public class CreateListCheck implements ElementTestCreator {
	private AssertTextEquals assertEquals;
	
	private String textExpected;	
	private DynamicTestData testData;	
	private XmlInfo testInfo;	
	private ControlTest controlTest;
	private TestAdderWithData testAdderWithData;
	
	public CreateListCheck(
		XmlInfo testInfo, DynamicTestData testData, 
		ControlTest controlTest, TestAdderWithData testAdderWithData) {

		this.testInfo = testInfo;
		this.testData = testData;
		this.testAdderWithData = testAdderWithData;
		this.controlTest = controlTest;
	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {
		//noit implemented
//		assertEquals = 
//				new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl);
//		assertEquals.assertTextEquals(textExpected);		
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
//					fail("ElementTestFactory.createTextListCheck not implemented.");
//					LogManager
//						.getLogger(CreateListCheck.class)
//						.error("ElementTestFactory.createTextListCheck not implemented.");
//				}));
//	}
		
}
