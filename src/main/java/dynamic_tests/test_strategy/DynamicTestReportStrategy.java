/**
 * 
 */
package dynamic_tests.test_strategy;

import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestIgnored;
import dynamic_tests.test_results.DynamicTestPass;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface DynamicTestReportStrategy {
	void reportPass(DynamicTestPass result);
	void reportFail(DynamicTestFail result);
	void reportIgnored(DynamicTestIgnored result);
}
