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
	private DataInserter dataInserter;
	
	public TestAdderComboSelectOnly(ElementCreation el) {
		this.dataIn = el.getTestDataIn().get(0);	
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
		testFactory.createTextCheck(details, this);					
	}

	@Override //TestAdderWithData
	public DataInserter getDataInserter() {
		if(dataInserter==null) dataInserter = new DataInserterItem(dataIn);
		return dataInserter;
	}
		
}
