/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.EmployeeLookupByName;
import controls.data_inserters.TestDataInserter;
import object_models.panels.JsPanel;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class DataInserterFactory_Tests {

	@Test
	void test_validInserter() {
		final JsPanel NULL_PANEL = null;
		TestDataIn dataIn = 
			new TestDataIn()
				.setInsertWith("EmployeeLookupByName")
				.setTestData(new TestDataText().setValue("some text"));
		
		TestDataInserter dataInserter = 
				DataInserterFactory.getDataInserter(NULL_PANEL, dataIn);
		
		assertTrue(dataInserter instanceof EmployeeLookupByName);
	}

}
