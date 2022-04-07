/**
 * 
 */
package dynamic_tests.test_strategy;

import dynamic_tests.test_results.DynamicTestData;
import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestIgnored;
import dynamic_tests.test_results.DynamicTestPass;

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
		DynamicTestData data = result.getData();
		System.out.println(
			String.format(
					"%s.%s.%s -> FAIL[expected=%s, was=%s]", 
					data.getTestSuiteName(), data.getElementName(), data.getElementType(), 
					data.getExpectedResult(), data.getActualResult()));  	
	}

	@Override
	public void reportIgnored(DynamicTestIgnored result) {
		System.out.println("IGNORE"); // TODO - remove or log
	}

}
