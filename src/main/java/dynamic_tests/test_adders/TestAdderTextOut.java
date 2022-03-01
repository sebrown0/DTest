/**
 * 
 */
package dynamic_tests.test_adders;

import java.util.Optional;

import controls.ControlTest;
import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.TestDataInserter;
import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 */
public class TestAdderTextOut implements TestAdderWithData {
	private TestDataIn dataIn;
	private TestDataOut dataOut;

	public TestAdderTextOut(ElementCreation el) {
		this.dataIn = el.getTestDataIn();
		//check if needed!!!!!!!!!!!!!!!!!!
		this.dataOut = el.getTestDataOut();
	}

	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
//		checkDataIn(testFactory);
		testFactory.createTextCheck(this);
	}
	
//	private void checkDataIn(ElementTestFactory testFactory) {		
//		if(dataIn != null) {
//			getDataInserter(testFactory)
//				.ifPresent(inserter -> inserter.insertData());
//		}
//	}
	
//	private Optional<TestDataInserter> getDataInserter(ElementTestFactory testFactory) {
//		ControlTest controlTest = testFactory.getControlTest();
//		return	
//			Optional.ofNullable(
//					DataInserterFactory.getDataInserter(controlTest, dataIn));
//	}
	
	
	@Override
	public TestDataIn getTestDataIn() {
		return dataIn;
	}
	@Override
	public TestDataOut getTestDataOut() {
		return dataOut;
	}

}
