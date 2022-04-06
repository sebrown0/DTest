/**
 * 
 */
package dynamic_tests.test_strategy;

import dynamic_tests.test_results.DynamicTestResult;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface DynamicTestReportStrategy {
	void reportResult(DynamicTestResult result);
}
