/**
 * 
 */
package exceptions;

/**
 * @author SteveBrown
 *
 */
public class IncorrectPageException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectPageException(String errMsg) {
		super(errMsg);
	}
}
