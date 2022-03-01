/**
 * 
 */
package dynamic_tests.test_data;

import java.util.Optional;

import controls.ControlTest;
import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.TestDataInserter;
import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.jaxb.pom.test_data.TestDataIn;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestDataInserter_XXXX {	
	private TestDataIn dataIn;
	private ElementTestFactory testFactory;
		
	public TestDataInserter_XXXX(TestDataIn dataIn, ElementTestFactory testFactory) {
		this.dataIn = dataIn;
		this.testFactory = testFactory;
	}

	public void insertData() {
		checkDataIn();
	}
	
	private void checkDataIn() {		
		if(dataIn != null) {
			getDataInserter()
				.ifPresent(inserter -> inserter.insertData());
		}
	}
	
	private Optional<TestDataInserter> getDataInserter() {
		ControlTest controlTest = testFactory.getControlTest();
		return	
			Optional.ofNullable(
					DataInserterFactory.getDataInserter(controlTest, dataIn));
	}
}
