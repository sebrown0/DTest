/**
 * 
 */
package dynamic_tests.test_adders;

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
	private String elName;
	private TestDataIn dataIn;
	private TestDataOut dataOut;

	public TestAdderTextOut(ElementCreation el) {		
		this.elName = el.getElementName();
		this.dataIn = el.getTestDataIn();
		this.dataOut = el.getTestDataOut();
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory) {
		testFactory.createTextCheck(elName, this);
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
