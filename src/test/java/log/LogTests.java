package log;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;


class LogTests {

	@Test
	void getRootLogger() {
		Logger logger = LogManager.getLogger();	
		logger.debug("a debug msg");
	}
	
	@Test
	void getTestLogger() {
		Logger logger = LogManager.getLogger("TEST_LOGGER");
//		Level l = Level.forName("TEST", 0);
		logger.log(logger.getLevel(), "a test msg");
	}
}
