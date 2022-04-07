/**
 * 
 */
package dynamic_tests.assertations;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_elements.TestElementData;
import dynamic_tests.test_elements.TestElementDetails;
import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestPass;
import dynamic_tests.test_results.DynamicTestSuiteData;
import dynamic_tests.test_strategy.DynamicTestReportStrategy;

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
public class AssertTextEquals implements ReportData {
	private DynamicTestSuiteData testSuiteData;
	@SuppressWarnings("unused")
	private ControlTest controlTest;
	private Optional<Control> cntrl;
//	private String testElmntName;
	private TestElementData testElementData;
	private TestElementDetails testElementDetails;
	
//	private String testElmntType;
	private List<String> includeInReport;
	private DynamicTestReportStrategy strat;
	
	@SuppressWarnings("unused")
	private final Logger LOGGER = LogManager.getLogger(AssertTextEquals.class);
	
	public AssertTextEquals(
			final XmlInfo testInfo, ControlTest controlTest, 
			DynamicTestSuiteData testData) {
			
			this.strat = testInfo.getTestReportStrategy();
			this.includeInReport = testInfo.getReportOnTests();
			this.controlTest = controlTest;
			this.testSuiteData = testData;			
		}
	
	@Override
	public String getTestSuiteName() {
		return testSuiteData.getTestSuiteName();
	}
	@Override
	public String getElementName() {
		return testElementDetails.getName();
	}
	@Override
	public String getElementType() {
		return testElementDetails.getElementType();
	}
	@Override
	public Object getElementTestType() {
		return testElementDetails.getElementTestType();
	}	
	@Override
	public String getExpected() {		 
		return testElementData.getTextExpected();
	}
	@Override
	public String getActual() {
		return testElementData.getTextActual();
	}
	
	public void assertTextEquals(TestElementDetails testElementDetails, TestElementData testElementData, Optional<Control> cntrl) {
		this.testElementDetails = testElementDetails;
		this.testElementData = testElementData;
		this.cntrl = cntrl;
		runAssert();
	}
	
	public void getTextActualAndAssertTextEquals(TestElementDetails testElementDetails, TestElementData testElementData, Optional<Control> cntrl) {
		this.testElementDetails = testElementDetails;
		this.testElementData = testElementData;
		this.cntrl = cntrl;
		setTextActual();
		runAssert();
	}

	private void setTextActual() {
		testElementData.setTextActual(ControlTestData.getControlText(cntrl));
	}
	
//	private void updateTestData() {
////		testData
//////			.setElementName(testElmntName)
////			.setTestType(testElmntType)
////			.setExpectedResult(textExpected)
////			.setActualResult(textActual);
//	}

	private void runAssert() {
//		updateTestData();
		if(getActual().equals(getExpected())) {
			if(isIncludedInReport("Passed")) {
				strat.reportPass(new DynamicTestPass(this));	
			}			
		}else {
			if(isIncludedInReport("Fails")) {
				strat.reportFail(new DynamicTestFail(this));	
			}			
		}
	}
	
	private boolean isIncludedInReport(String testType) {
		if(includeInReport.contains("All")) {
			return true;
		}else if(includeInReport.contains(testType)) {
			return true;
		}
		return false;
	}
	
	public void assertTextEquals(TestElementDetails testElementDetails, TestAdderWithData testAdder, Optional<Control> cntrl) {
		System.out.println("assertTextEquals->NOT IMPLEMENTED"); // TODO - remove or log 	
//		this.testElmntName = testElementDetails.getName();
//		this.testElmntType = testElementDetails.getElementType();
//		this.cntrl = cntrl;
//		
//		TestDataOut dataOut = testAdder.getTestDataOut();
//		if(dataOut != null) {
//			Data testData = dataOut.getData();
//			//TODO
//			if(testData != null) {
//				TestDataItem testDataItem = testData.getTestDataList().get(0);
//				if(testDataItem != null) {
//					this.textExpected = testDataItem.getValue();
//					runAssert();	
//				}
////			runAssert(testData.getValue());						
//			}					
//		}else {
//			LOGGER
//				.info(
//					String.format(
//							"No test data to check expected result for [%s - %s]", 
//							controlTest.getClass().getSimpleName(), cntrl.get().getClass().getSimpleName()));
//		}				
	}



	
	
}
