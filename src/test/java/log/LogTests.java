package log;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import listeners.TestResultLogger;

@ExtendWith(TestResultLogger.class)

class LogTests {
	
	/*
	 * Get the root logger.
	 * Check the logs/app.log file to check that the messages were added.
	 */
	@Test	
	@Tag("R1")
	@Tag("T1")
	void getRootLogger() {
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
		logger.debug("a debug msg");
		logger.error("an error msg");
	}
	
	/*
	 * Result should be written to the test log.
	 */
	@Test
	@Tag("R1")
	@Tag("T2")
	@Disabled("because I can!")
	void testIsIgnored() {		
		assertTrue(true);
	}
	
	/*
	 * Result should be written to the test log.
	 * Should include the test run/case IDs.
	 */
	@Test
	@Tag("R1")
	@Tag("T3")
	void failedTestWithTags() {    
		fail("failed test message");
	}
}
