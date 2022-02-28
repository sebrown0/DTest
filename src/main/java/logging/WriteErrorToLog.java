/**
 * 
 */
package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class WriteErrorToLog implements Runnable{
	private String msg;
	private Logger logger;
	
	public WriteErrorToLog(String msg, Class<?> forClass) {
		this.msg = msg;
		this.logger = LogManager.getLogger(forClass);
	}
	
	@Override
	public void run() {
		logger.error(msg);
	}

}
