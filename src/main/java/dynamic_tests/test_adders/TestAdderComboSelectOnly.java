/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.DataInserter;
import dynamic_tests.test_elements.DataInserterItem;
import dynamic_tests.test_elements.ElementTestFactory;
import dynamic_tests.test_elements.TestElementDetails;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 */
public class TestAdderComboSelectOnly implements TestAdderWithData {
	private TestDataItem dataIn;
	
	public TestAdderComboSelectOnly(ElementCreation el) {
		this.dataIn = el.getTestDataIn().get(0);	
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
//		if(dataOut != null) {			
			testFactory.createTextCheck(details, this);			
//		}		
	}

	@Override //TestAdderWithData
	public DataInserter getDataInserter() {
		return new DataInserterItem(dataIn, null, null);
	}
		
}
