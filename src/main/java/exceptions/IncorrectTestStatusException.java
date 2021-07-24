/**
 * 
 */
package exceptions;

/**
 * @author Brown
 *
 */
public class IncorrectTestStatusException extends Exception {	
	private static final long serialVersionUID = 1L;

	public IncorrectTestStatusException(String errMsg) {
		super(errMsg);
	}
}
