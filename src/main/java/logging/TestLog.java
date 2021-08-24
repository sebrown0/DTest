/**
 * 
 */
package logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import test_result.TestResultGetter;

/**
 * @author Steve Brown
 * @params
 *  logDir: where the log is kept. Specified in config.xml.
 *  testSuiteName: forms part of the name of the log result file.
 *  
 * Write the result to the test log in the format
 * specified in constructLogMsg().
 */
public class TestLog {
	private Logger logger;
	private String testSuiteName;
	private String logDir;
	
	public TestLog(String logDir, String testSuiteName) {
		this.testSuiteName = testSuiteName;
		this.logDir = logDir;
		configureLog();
	}
	
	private void configureLog() {
		setLogFilePathAndName();
		resetContext();
		setLogger();
	}
	
	private void setLogFilePathAndName() {
		System.setProperty("logFilePath", logDir + "/" + getFileName());	
	}
	
	private void resetContext() {
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		ctx.reconfigure();
	}
	
	private void setLogger() {
		logger = LogManager.getLogger("TEST_LOGGER");
	}
	
	private String getFileName() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH;mm;ss");
		return testSuiteName + "_" + now.format(formatter) + ".txt";
	}
	
	public void writeTestResultToLog(TestResultGetter resultGetter) {
		logger.log(resultGetter.getLogLevel(), constructLogMsg(resultGetter));
	}
	
	private String constructLogMsg(TestResultGetter resultGetter) {		
		return 	
				"[TEST: " + 
				resultGetter.getTestName() + "], [ID: " + 
				resultGetter.getTestIds() + "], [COMMENTS: " +
				resultGetter.getTestComments() + "]";
	}
}
