/**
 * 
 */
package dynamic_tests.test_strategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestPass;
import dynamic_tests.test_results.DynamicTestResult;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestReportStrategyJunit implements DynamicTestReportStrategy {

	@Override
	public void reportResult(DynamicTestResult result) {
		if(result instanceof DynamicTestPass) {
			assertTrue(true);
		}else if(result instanceof DynamicTestFail) {
			assertTrue(false);
		}
	}

}
