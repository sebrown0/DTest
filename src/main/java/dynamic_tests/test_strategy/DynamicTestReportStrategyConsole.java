/**
 * 
 */
package dynamic_tests.test_strategy;

import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestPass;
import dynamic_tests.test_results.DynamicTestResult;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestReportStrategyConsole implements DynamicTestReportStrategy {

	@Override
	public void reportResult(DynamicTestResult result) {
		if(result instanceof DynamicTestPass) {
			System.out.println("FAIL"); // TODO - remove or log 	
		}else if(result instanceof DynamicTestFail) {
			System.out.println("PASS"); // TODO - remove or log
		}
	}

}
