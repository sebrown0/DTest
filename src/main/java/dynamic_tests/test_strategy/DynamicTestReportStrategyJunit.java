/**
 * 
 */
package dynamic_tests.test_strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;

import dynamic_tests.test_results.DynamicTestFail;
import dynamic_tests.test_results.DynamicTestIgnored;
import dynamic_tests.test_results.DynamicTestPass;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Leverage JUnit to report the results.
 */
public class DynamicTestReportStrategyJunit implements DynamicTestReportStrategy {

	@Override
	public void reportPass(DynamicTestPass result) {
		assertTrue(true);
	}

	@Override
	public void reportFail(DynamicTestFail result) {
		assertEquals(result.getExpected(), result.getActual());
	}

	@Override
	public void reportIgnored(DynamicTestIgnored result) {
		LogManager
			.getLogger(DynamicTestReportStrategyJunit.class)
			.error("ReportIgnored not implemented for JUnit strategy.");
	}

}
