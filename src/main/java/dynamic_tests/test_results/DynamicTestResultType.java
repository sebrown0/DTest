/**
 * 
 */
package dynamic_tests.test_results;

import dynamic_tests.assertations.ReportData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Is Pass, Fail, Ignored with ReportData for the test.
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

}
