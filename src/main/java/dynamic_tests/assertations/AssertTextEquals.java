/**
 * 
 */
package dynamic_tests.assertations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_results.DynamicTestData;
import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestPass;
import dynamic_tests.test_strategy.DynamicTestReportStrategy;
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
//	private DynamicTestData testResultData;
	private ControlTest controlTest;
	private Optional<Control> cntrl;
	private String textActual;
	
	private DynamicTestReportStrategy strat;
	
	private final Logger LOGGER = LogManager.getLogger(AssertTextEquals.class);
	
	public AssertTextEquals(DynamicTestReportStrategy strat, ControlTest controlTest, Optional<Control> cntrl) {
		this.strat = strat;
		this.controlTest = controlTest;
		this.cntrl = cntrl;
	}
	
	public void assertTextEquals(String textExpected) {
		setTextActual();
		runAssert(textExpected);
	}

	private void setTextActual() {
		textActual = ControlTestData.getControlText(cntrl);
	}
	
	public void runAssert(String expected) {
//		DynamicTestResult result = null;
		
//		Control c = cntrl.get();
		
//		System.out.println("Actual [" + textActual + "]" ); // TODO - remove or log 	
//		System.out.println("Expected [" + expected + "]\n" ); // TODO - remove or log
		expected+="**";
		
		if(textActual.equals(expected)) {
			strat.reportResult(new DynamicTestPass());
//			testResultData.setResult(new DynamicTestPass());			
//			assertTrue(true);
		}else {
			strat.reportResult(new DynamicTestFail());
//			testResultData.setResult(new DynamicTestFail());
//			assertEquals(expected, textActual);
			
//			fail(String.format("Expected [%s] but was [%s]", expected, textActual));
		}
		
//		assertEquals(expected, textActual);
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
	
}
