/**
 * 
 */
package reporting.strategy;

import exceptions.InvalidReportWriter;
import test_result.TestResultGetter;

/**
 * @author SteveBrown
 *
 */
public interface ResultWriter {
	void checkWriter() throws InvalidReportWriter;
	void writeResult(TestResultGetter resultGetter);
}
