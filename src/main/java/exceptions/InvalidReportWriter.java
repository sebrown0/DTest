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
public class InvalidReportWriter extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidReportWriter(String errMsg) {
		super(errMsg);
		Logger logger = LogManager.getLogger(this.getClass().getSimpleName());	
		logger.info(errMsg);
	}
}