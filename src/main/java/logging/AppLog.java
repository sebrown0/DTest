/**
 * 
 */
package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * 
 */
public class AppLog{
	private Logger logger;
	
	public <T> AppLog(Class<T> clazz) {
		this.logger = LogManager.getLogger(clazz);
	}

	public void writeInfoMsg(String msg) {
		logger.info(msg);
	}
	
}
