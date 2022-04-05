/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.EmployeeLookupByName;
import controls.data_inserters.TestDataInserter;
import object_models.panels.JsPanel;
import site_mapper.jaxb.pom.test_data.Data;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataItem;


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
		final int FIRST_ITEM = 0;
		
		TestDataItem testDataItem = new TestDataItem();
		testDataItem
			.setId("name")
			.setInsertWith("EmployeeLookupByName")
			.setValue("a name");
		
		Data data = new Data();
		data.setTestDataList(Arrays.asList(testDataItem));
		
		TestDataIn dataIn = 
			new TestDataIn()
				.setData(data);
		
		TestDataInserter dataInserter = 
				DataInserterFactory.getDataInserter(FIRST_ITEM, NULL_PANEL, dataIn);
		
		assertTrue(dataInserter instanceof EmployeeLookupByName);
	}

}
