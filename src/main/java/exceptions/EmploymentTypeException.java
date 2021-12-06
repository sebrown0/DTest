/**
 * 
 */
package exceptions;

import org.apache.logging.log4j.LogManager;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 *
 */
public class EmploymentTypeException extends Exception implements Runnable {
	private String errMsg;
	private static final long serialVersionUID = 1L;

	public EmploymentTypeException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;		
		reportError();
	}
	
	public void reportError() {
		LogManager
			.getLogger(EmploymentTypeException.class.getName())
			.error(errMsg);
	}

	@Override
	public void run() {
		reportError();
	}
}
