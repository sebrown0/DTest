/**
 * 
 */
package dynamic_tests.test_elements;

import controls.ControlTest;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_data.TestDataInput;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestDataInserter {
	public static void insertAnyTestData(
		TestAdderWithData testData, ControlTest controlTest) {
		
		TestDataInput dataInserter = 
				new TestDataInput(testData.getTestDataIn(), controlTest);
		
		dataInserter.insertData();
		
	}
}
