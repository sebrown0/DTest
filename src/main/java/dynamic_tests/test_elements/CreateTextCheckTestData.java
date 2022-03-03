/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckTestData extends TestCreator {
	private TestAdderWithData testData;
	
	public CreateTextCheckTestData(
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, TestAdderWithData testData) {
		super(cntrlFinder, testList, controlTest);

		this.testData = testData;
	}
	
	@Override
	public void createTest(String elName) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {
					getControlAndParent();
					TestDataInserter.insertAnyTestData(testData, controlTest);
					new 
						AssertTextEquals(controlTest, cntrl)
							.assertTextEquals(testData); 	
				}));	
	}
		
}
