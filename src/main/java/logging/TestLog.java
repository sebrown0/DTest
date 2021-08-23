/**
 * 
 */
package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test_result.TestResultGetter;

/**
 * @author Steve Brown
 *
 */
public class TestLog {
	private static Logger logger = LogManager.getLogger("TEST_LOGGER");

	public static void writeTestResultToLog(TestResultGetter resultGetter) {
		logger.log(resultGetter.getLogLevel(), constructLogMsg(resultGetter));
	}
	
	private static String constructLogMsg(TestResultGetter resultGetter) {		
		return 	
				"[TEST: " + 
				resultGetter.getTestName() + "], [ID: " + 
				resultGetter.getTestIds() + "], [COMMENTS: " +
				resultGetter.getTestComments() + "]";
	}
}
