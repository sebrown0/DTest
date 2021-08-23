/**
 * 
 */
package reporting.strategy;

import logging.TestLog;
import test_result.TestResultGetter;

/**
 * @author SteveBrown
 *
 */
public class LogWriter implements ResultWriter {

	@Override
	public void writeResult(TestResultGetter resultGetter) {		
		TestLog.writeTestResultToLog(resultGetter);
	}
}
