/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckTestData extends TestElementCreator {
	private TestAdderWithData testAdderWithData;		
	
	public CreateTextCheckTestData(
		TestElementDetails testElementDetails, XmlInfo testInfo, DynamicTestSuiteData testData, 
		ControlTest controlTest,	TestAdderWithData testAdderWithData) {
		
		super(testElementDetails, testInfo, testData, controlTest, null);
		
		this.testAdderWithData = testAdderWithData;
	}

	@Override
	protected void executeTest(Optional<Control> cntrl) {
		ZZZ_TestDataInserter.insertAnyTestData(testAdderWithData, controlTest);		
		assertEquals.assertTextEquals(testElementDetails, testAdderWithData, cntrl);		
	}

	@Override
	protected String getMessage() {
		return "Is [" + testElementDetails.getName() +"] text correct?";
	}
	
	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("TestDataCheck");		
	}
}
