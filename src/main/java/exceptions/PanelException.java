/**
 * 
 */
package exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 */
public class PanelException extends Exception implements Runnable {
	private String errMsg;
	private static final long serialVersionUID = 1L;

	public PanelException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;		
	}
	
	public static void reportError(String errMsg) {
		Logger logger = LogManager.getLogger(PanelException.class.getName());	
		logger.error(errMsg);
	}

	@Override
	public void run() {
		reportError(errMsg);
	}
}
