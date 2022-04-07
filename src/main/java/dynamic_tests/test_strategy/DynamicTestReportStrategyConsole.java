/**
 * 
 */
package dynamic_tests.test_strategy;

import dynamic_tests.assertations.ReportData;
import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestIgnored;
import dynamic_tests.test_results.DynamicTestPass;
import dynamic_tests.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestReportStrategyConsole implements DynamicTestReportStrategy {

	@Override
	public void reportPass(DynamicTestPass result) {
		System.out.println("PASS"); // TODO - remove or log		
	}

	@Override
	public void reportFail(DynamicTestFail result) {
//		DynamicTestSuiteData data = result.getData();
		ReportData data = result.getReportData();
		System.out.println(
			String.format(
					"%s.%s.%s.%s -> FAIL[expected=%s, was=%s]", 
					data.getTestSuiteName(), data.getElementName(), data.getElementType(), data.getElementTestType(), 
					data.getExpected(), data.getActual()));  	
	}

	@Override
	public void reportIgnored(DynamicTestIgnored result) {
		System.out.println("IGNORE"); // TODO - remove or log
	}

}
