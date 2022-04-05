/**
 * 
 */
package dynamic_tests.test_data;

import java.util.List;
import java.util.Optional;

import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.TestDataInserter;
import controls.interfaces.ControlTest;
import exceptions.InvalidArgumentException;
import site_mapper.jaxb.pom.test_data.Data;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataItem;

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
	
		Data testData = dataIn.getData();
		List<TestDataItem> testDataList = testData.getTestDataList();
		testDataList.forEach(itm -> {
			try {
				checkDataIn(itm);
			} catch (InvalidArgumentException e) {
				// Report to the screen so we know.
				System.out.println("TestDataInput -> " + e); // TODO - remove or log 	
			}
			
			getDataInserter(0).ifPresent(inserter -> inserter.insertData());	
		});
		
	}
	
	private void checkDataIn(TestDataItem item) throws InvalidArgumentException {		
		if(dataIn != null) {
//			TestData testData = dataIn.getTestData();
			
			if(isValidTestDataIn(item)) {				
					throw new InvalidArgumentException(
							TestDataInput.class, 
							"Only text is accepted as data in. List not currently supported.");					
			}					
		}
	}
	
	private boolean isValidTestDataIn(TestDataItem item) {
//		return (testData != null && ((testData instanceof TestDataText)==false));
		/*
		 * Only checking for null.
		 * TODO Check value, insert with etc.
		 */
		return (item != null);
	}
	
	private Optional<TestDataInserter> getDataInserter(int idx) {
		return	
			Optional.ofNullable(
					DataInserterFactory.getDataInserter(idx, controlTest, dataIn));
	}
}
