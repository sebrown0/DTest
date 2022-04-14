/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_results.DynamicTestSuiteData;
import library.common.controls.interfaces.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Create the test for the element.
 */
public abstract class TestElementCreator {	
	protected TestElementDetails testElementDetails;
	protected String textExpected;	
	protected DynamicTestSuiteData testData;	
	protected XmlInfo testInfo;	
	protected AssertTextEquals assertEquals;
	
	public TestElementCreator(
			TestElementDetails testElementDetails, XmlInfo testInfo, 
			DynamicTestSuiteData testData, String textExpected) {

			this.testElementDetails = testElementDetails;
			this.testInfo = testInfo;
			this.testData = testData;
			this.textExpected = textExpected;			
			this.assertEquals = new AssertTextEquals(testInfo, testData);
		}
		
	protected abstract String getMessage();
	protected abstract void executeTest(Optional<Control> cntrl);
	protected abstract void setTestType();
	
	public void createTestExecutor(Optional<Control> cntrl) {
		setTestType();
		executeTest(cntrl);
	}
}
