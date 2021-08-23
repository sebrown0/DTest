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
import logging.AppLog;

@ExtendWith(TestResultLogger.class)
class LogTests {

	@Test	
	void getRootLogger() {
		Logger logger = LogManager.getLogger();	
		logger.debug("a debug msg");
		logger.error("an error msg");
	}
		
	@Test 
	void getAppLogger_FromFactory(){
		AppLog log = new AppLog(this.getClass());
		log.writeInfoMsg("info msg 1");		
	}
	
	@Test
	@Disabled
	void testIsIgnored() {
		assertTrue(true);
	}
	
	@Test
	@Tag("R1")
	@Tag("T1")
	void failedTestWithTags() {
		fail("failed test message");
	}
}
