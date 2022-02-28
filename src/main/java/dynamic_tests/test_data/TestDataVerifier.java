/**
 * 
 */
package dynamic_tests.test_data;

import dynamic_tests.test_adders.TestAdderWithData;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataList;
import site_mapper.jaxb.pom.test_data.TestDataOut;
import site_mapper.jaxb.pom.test_data.TestDataText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	initial
 * @since 1.0
 */
public class TestDataVerifier {
	private TestDataIn dataIn;
	private TestDataOut dataOut;
	
	public TestDataVerifier(TestAdderWithData testAdder) {
		this.dataIn = testAdder.getTestDataIn();
		this.dataOut = testAdder.getTestDataOut();
	}
	
	public void verifyDataFor() {
		insertDataInIfPresent();
	}

	private void insertDataInIfPresent() {
		if(dataIn != null) {
			var testData = dataIn.getTestData();
			if(testData instanceof TestDataList) {
				System.out.println("is LIST"); // TODO - remove or log 	
			}else if(testData instanceof TestDataText) {
				System.out.println("is TEXT"); // TODO - remove or log
				
			}else {
				System.out.println("OH NO"); // TODO - remove or log
			}
		}
	}
}
