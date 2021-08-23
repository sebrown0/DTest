/**
 * 
 */
package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * 
 * Get a log instance, using [log4j2.xml],
 * for either the app (debug, error etc), or
 * a test (reporting).
 */
public class LogFactory {	
	
	public static <T> Logger getAppLog(Class<T> clazz) {
		return LogManager.getLogger(clazz.getClass());
	}
	
	public static Logger getTestLog() {
		return LogManager.getLogger("TEST_LOGGER");
	}
}
