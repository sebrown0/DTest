/**
 * 
 */
package reporting.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.InvalidReportWriter;
import test_result.TestResultGetter;

/**
 * @author Steve Brown
 *
 */
public class ConsoleWriter implements ResultWriter {
	@Override
	public void writeResult(TestResultGetter resultGetter) {
		System.out.println(resultGetter.getResult().toString());
	}

	@Override
	public void checkWriter() throws InvalidReportWriter {
		// Console should always be available.
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
		logger.info("Using Console for reporting");		
	}
}
