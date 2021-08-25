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
public class ElementDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public ElementDoesNotExistException(String errMsg) {
		super(errMsg);
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
		logger.error(errMsg);
	}
}
