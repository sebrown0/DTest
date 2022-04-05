/**
 * 
 */
package dynamic_tests.assertations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.test_adders.TestAdderWithData;
import site_mapper.jaxb.pom.test_data.Data;
import site_mapper.jaxb.pom.test_data.TestDataItem;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * TODO
 * ----
 * Split this up!!!
 * 
 */
public class AssertTextEquals {
	private ControlTest controlTest;
	private Optional<Control> cntrl;
	private String textActual;
	
	private final Logger LOGGER = LogManager.getLogger(AssertTextEquals.class);
	
	public AssertTextEquals(ControlTest controlTest, Optional<Control> cntrl) {
		this.controlTest = controlTest;
		this.cntrl = cntrl;
	}

	private void setTextActual() {
		textActual = ControlTestData.getControlText(cntrl);
	}
	
	public void assertTextEquals(String textExpected) {
		setTextActual();
		runAssert(textExpected);
	}
	
	public void runAssert(String expected) {
		assertEquals(expected, textActual);
	}
	
	public void assertTextEquals(TestAdderWithData testAdder) {
		setTextActual();
		
		TestDataOut dataOut = testAdder.getTestDataOut();
		if(dataOut != null) {
			Data testData = dataOut.getData();
			//TODO
			if(testData != null) {
				TestDataItem testDataItem = testData.getTestDataList().get(0);
				if(testDataItem != null) {
					runAssert(testDataItem.getValue());	
				}
//			runAssert(testData.getValue());						
			}
			
					
		}else {
			LOGGER
				.info(
					String.format(
							"No test data to check expected result for [%s - %s]", 
							controlTest.getClass().getSimpleName(), cntrl.get().getClass().getSimpleName()));
		}				
	}
	
//	public void assertTextEquals(TestAdderWithData testAdder) {
//		setTextActual();
//		
//		TestDataOut dataOut = testAdder.getTestDataOut();
//		if(dataOut != null) {
//			Data testData = dataOut.getData();
//			if(testData instanceof TestDataList) {
//				LOGGER.error("TestDataList not implemented");
//				assertTrue(false);
//			}else {
//				runAssert(testData.getValue());	
//			}			
//		}else {
//			LOGGER
//				.info(
//					String.format(
//							"No test data to check expected result for [%s - %s]", 
//							controlTest.getClass().getSimpleName(), cntrl.get().getClass().getSimpleName()));
//		}				
//	}

}
