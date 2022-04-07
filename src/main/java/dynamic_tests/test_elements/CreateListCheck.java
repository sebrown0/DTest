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
@SuppressWarnings("unused")
public class CreateListCheck extends TestElementCreator {	
	private TestAdderWithData testAdderWithData;
	private AssertTextEquals assertEquals;
	
	public CreateListCheck(String elmntName, XmlInfo testInfo, DynamicTestData testData, ControlTest controlTest,
			TestAdderWithData testAdderWithData) {
		super(elmntName, testInfo, testData, controlTest, null);

		this.testAdderWithData = testAdderWithData;
	}

		
	@Override
	public void executeTest(Optional<Control> cntrl) {
		//noit implemented			
	}

	@Override
	public String getMessage() {
		return "Is [" + testData.getElementName() +"] text correct?";
	}
			
}
