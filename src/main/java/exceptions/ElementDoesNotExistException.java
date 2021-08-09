/**
 * 
 */
package exceptions;

/**
 * @author Steve Brown
 *
 */
public class ElementDoesNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public ElementDoesNotExistException(String errMsg) {
		super(errMsg);
	}
}
