/**
 * 
 */
package dynamic_tests.test_results;

import dynamic_tests.assertations.ReportData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestResultType implements DynamicTestResult {
	private ReportData data;
			
	public DynamicTestResultType(ReportData data) {
		this.data = data;
	}
	

	@Override
	public ReportData getReportData() {
		return data;
	}
	
//	data.getTestSuiteName(), data.getElementName(), data.getElementType()
//	@Override
//	public String getExpected() {		
//		return data.getExpectedResult();
//	}
//
//	@Override
//	public String getActual() {
//		return data.getActualResult();
//	}


//	public DynamicTestSuiteData getData() {
//		return data;
//	}


}
