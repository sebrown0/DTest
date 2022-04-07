/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.Optional;

import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_results.DynamicTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Create the test for the element.
 */
public abstract class TestElementCreator {	
	protected String elmntName;
	protected String textExpected;	
	protected DynamicTestData testData;	
	protected XmlInfo testInfo;	
	protected ControlTest controlTest;
	
	public TestElementCreator(
			String elmntName,
			XmlInfo testInfo, DynamicTestData testData, 
			ControlTest controlTest, String textExpected) {

			this.elmntName = elmntName;
			this.testInfo = testInfo;
			this.testData = testData;
			this.textExpected = textExpected;
			this.controlTest = controlTest;
		}
	
	public abstract String getMessage();
	public abstract void executeTest(Optional<Control> cntrl);
}
