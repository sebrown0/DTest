/**
 * 
 */
package dynamic_tests.test_adders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestData;
import dynamic_tests.test_elements.TestDataInserter;
import site_mapper.elements.ElementDetails;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataList;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class TestAdderTextIn extends XXTestAdder {
	private ElementDetails elDetails;
	
	public TestAdderTextIn(ElementDetails elDetails) {
		this.elDetails = elDetails;
	}
	
	@Override
	public DynamicTest addTest() {
		TestDataIn dataIn = elDetails.getTestDataIn();
		if(dataIn != null) {
			TestDataInserter.insertAnyTestData((TestAdderWithData)dataIn, controlTest);
		}
				
		TestDataOut dataOut = elDetails.getTestDataOut();
		if(dataOut != null) {
			if(dataOut.getTestData() instanceof TestDataList) {
				LogManager.getLogger(TestAdderTextIn.class).error("TestDataList NOT IMPLEMENTED");
				return getEmptyTest();		
			}else {
				return getTest(dataOut.getTestData().getValue());		
			}
		}
		
		return getNoDataTest();
	}
	
	private DynamicTest getTest(String exp) {
		return 
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {							
					assertEquals(exp, ControlTestData.getControlText(getControl()));
				});
	}
	
	private DynamicTest getEmptyTest() {
		return 
			DynamicTest.dynamicTest(
				"[" + elName +"] - TestDataList NOT IMPLEMENTED", 
				() -> {							
					assertTrue(true);
				});
	}
	
	private DynamicTest getNoDataTest() {
		return 
			DynamicTest.dynamicTest(
				"[" + elName +"] - No Data Supplied", 
				() -> {							
					assertTrue(true);
				});
	}
	
}
