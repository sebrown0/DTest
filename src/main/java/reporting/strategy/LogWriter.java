/**
 * 
 */
package reporting.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.InvalidReportWriter;
import logging.TestLog;
import test_result.TestResultGetter;

/**
 * @author Steve Brown
 *
 * Write the results to the log file specified in log4j2.xml.
 */
public class LogWriter implements ResultWriter {
	private TestLog log;
	
	public LogWriter(String logDir, String testSuiteName) {
		log = new TestLog(logDir, testSuiteName);
	}
	
	@Override
	public void writeResult(TestResultGetter resultGetter) {		
		log.writeTestResultToLog(resultGetter);
	}
	
	@Override
	public void checkWriter() throws InvalidReportWriter {
		// Assuming that we can always write to the log file.
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
		logger.info("Using Log File for reporting");		 
	}
}
