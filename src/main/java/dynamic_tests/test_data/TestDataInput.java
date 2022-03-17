/**
 * 
 */
package dynamic_tests.test_data;

import java.util.Optional;

import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.TestDataInserter;
import controls.interfaces.ControlTest;
import exceptions.InvalidArgumentException;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Checks if there's any test data to input.
 * If there is, a TestDataInserter is created
 * to insert the data.
 */
public class TestDataInput {	
	private TestDataIn dataIn;
	private ControlTest controlTest;
		
	public TestDataInput(TestDataIn dataIn, ControlTest controlTest) {
		this.dataIn = dataIn;
		this.controlTest = controlTest;
	}

	public void insertData() {
		try {
			checkDataIn();
			getDataInserter().ifPresent(inserter -> inserter.insertData());	
		} catch (InvalidArgumentException e) {
			// Report to the screen so we know.
			System.out.println("TestDataInput -> " + e); // TODO - remove or log 	
		}
	}
	
	private void checkDataIn() throws InvalidArgumentException {		
		if(dataIn != null) {
			TestData testData = dataIn.getTestData();
			if(isValidTestDataIn(testData)) {				
					throw new InvalidArgumentException(
							TestDataInput.class, 
							"Only text is accepted as data in. List not currently supported.");					
			}					
		}
	}
	
	private boolean isValidTestDataIn(TestData testData) {
		return (testData != null && ((testData instanceof TestDataText)==false));
	}
	
	private Optional<TestDataInserter> getDataInserter() {
		return	
			Optional.ofNullable(
					DataInserterFactory.getDataInserter(controlTest, dataIn));
	}
}
