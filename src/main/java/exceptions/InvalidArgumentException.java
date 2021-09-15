/**
 * 
 */
package exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 *
 */
public class InvalidArgumentException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String errMsg) {
		super(errMsg);
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
		logger.info(errMsg);
	}
}