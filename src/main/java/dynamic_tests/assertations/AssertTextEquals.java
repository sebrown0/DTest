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
	private DynamicTestData testData;
	private ControlTest controlTest;
	private Optional<Control> cntrl;
	private String testElmntName;
	private String textActual;
	private String textExpected;
	private String testElmntType;
	private List<String> includeInReport;
	private DynamicTestReportStrategy strat;
	
	private final Logger LOGGER = LogManager.getLogger(AssertTextEquals.class);
	
	public AssertTextEquals(
		final XmlInfo testInfo, ControlTest controlTest, 
		DynamicTestData testData, Optional<Control> cntrl) {
		
		this.strat = testInfo.getTestReportStrategy();
		this.includeInReport = testInfo.getReportOnTests();
		this.controlTest = controlTest;
		this.testData = testData;
		this.cntrl = cntrl;
	}
	
	public void assertTextEquals(String testElmntName, String testElmntType, String textExpected, String textActual) {
		this.testElmntName = testElmntName;
		this.testElmntType = testElmntType;
		this.textActual = textActual;
		this.textExpected = textExpected;
		runAssert();
	}
	
	public void assertTextEquals(String testElmntName, String testElmntType, String textExpected) {
		this.testElmntName = testElmntName;
		this.testElmntType = testElmntType;
		this.textExpected = textExpected;
		setTextActual();
		runAssert();
	}

	private void setTextActual() {
		textActual = ControlTestData.getControlText(cntrl);
	}
	
	private void updateTestData() {
		testData
			.setElementName(testElmntName)
			.setTestType(testElmntType)
			.setExpectedResult(textExpected)
			.setActualResult(textActual);
	}

	private void runAssert() {
		updateTestData();
		if(textActual.equals(textExpected)) {
			if(isIncludedInReport("Passed")) {
				strat.reportPass(new DynamicTestPass(testData));	
			}			
		}else {
			if(isIncludedInReport("Fails")) {
				strat.reportFail(new DynamicTestFail(testData));	
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
	
	public void assertTextEquals(String testElmntName, String testElmntType, TestAdderWithData testAdder) {
		this.testElmntName = testElmntName;
		this.testElmntType = testElmntType;
		TestDataOut dataOut = testAdder.getTestDataOut();
		if(dataOut != null) {
			Data testData = dataOut.getData();
			//TODO
			if(testData != null) {
				TestDataItem testDataItem = testData.getTestDataList().get(0);
				if(testDataItem != null) {
					this.textExpected = testDataItem.getValue();
					runAssert();	
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
