/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import element_library.common.controls.data_inserters.DataInserterFactory;
import element_library.common.controls.data_inserters.TestDataInserter;
import element_library.common.panels.JsPanel;
import library.data_inserters.EmployeeLookupByName;
import site_mapper.jaxb.pom.test_data.TestData;
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
		
		
		
		TestData testData = new TestData();
		testData.setTestDataIn(Arrays.asList(testDataItem));
		
		TestDataInserter dataInserter = 
				DataInserterFactory.getDataInserter(FIRST_ITEM, NULL_PANEL, testData);
		
		assertTrue(dataInserter instanceof EmployeeLookupByName);
	}

}
