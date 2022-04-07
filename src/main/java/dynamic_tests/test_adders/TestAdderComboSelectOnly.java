/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import dynamic_tests.test_elements.TestElementDetails;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 */
public class TestAdderComboSelectOnly implements TestAdderWithData {
	private TestDataIn dataIn;
	private TestDataOut dataOut;

	public TestAdderComboSelectOnly(ElementCreation el) {
		this.dataIn = el.getTestDataIn();
		this.dataOut = el.getTestDataOut();
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
		if(dataOut != null) {			
			testFactory.createTextCheck(details, this);			
		}		
	}
		
	@Override //TestAdderWithData
	public TestDataIn getTestDataIn() {
		return dataIn;
	}
	@Override //TestAdderWithData
	public TestDataOut getTestDataOut() {
		return dataOut;
	}

}
