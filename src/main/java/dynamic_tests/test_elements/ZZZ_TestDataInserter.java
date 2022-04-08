/**
 * 
 */
package dynamic_tests.test_elements;

import controls.interfaces.ControlTest;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_data.TestDataInput;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ZZZ_TestDataInserter {
	public static void insertAnyTestData(
		TestAdderWithData testData, ControlTest controlTest) {
		
//		TestDataInput dataInserter = 
//				new TestDataInput(testData.getTestData(), controlTest);
		TestDataInput dataInserter = 
				new TestDataInput(null, controlTest);
		dataInserter.insertData();
		
	}
}