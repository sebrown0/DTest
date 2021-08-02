/**
 * 
 */
package reporting.strategy;

import test_result.TestResultGetter;

/**
 * @author SteveBrown
 *
 */
public interface ResultWriter {
	void writeResult(TestResultGetter resultGetter);
}
