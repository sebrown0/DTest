/**
 * 
 */
package dynamic_tests.test_adders;

import java.util.List;

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
public class TestAdderTextOut implements TestAdderWithData {
	private TestDataItem dataIn;
	
	public TestAdderTextOut(ElementCreation el) {		
		this.dataIn = el.getTestDataIn().get(0);	
	}

	@Override //TestAdder
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
		testFactory.createTextCheck(details, this);
	}

	@Override //TestAdderWithData
	public DataInserter getDataInserter() {
		return new DataInserterItem(dataIn, null, null);
	}

}
