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
public class PayrollAlreadyInitialisedException extends Exception implements Runnable {
	private String errMsg;
	private static final long serialVersionUID = 1L;

	public PayrollAlreadyInitialisedException(String errMsg) {
		super(errMsg);
		this.errMsg = errMsg;		
		reportError();
	}
	
	public void reportError() {
		LogManager
			.getLogger(PayrollAlreadyInitialisedException.class.getName())
			.error(errMsg);
	}

	@Override
	public void run() {
		reportError();
	}
}
