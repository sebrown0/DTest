/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckString extends TestCreator {
	private String textExpected;
	private ElementTestFactory tf;
	
	public CreateTextCheckString(
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String textExpected, ElementTestFactory tf) {
		super(cntrlFinder, testList, controlTest);

		this.textExpected = textExpected;
		this.tf = tf;
	}
	
	@Override
	public void createTest(String elName) {
		testList.add(
		DynamicTest.dynamicTest(
			"Is [" + elName +"] text correct?", 
			() -> {
				tf.loadItem();
				getControlAndParent();
				new 
					AssertTextEquals(controlTest, cntrl)
						.assertTextEquals(textExpected);
			}));		
	}
		
}
