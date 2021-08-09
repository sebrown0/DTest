/**
 * 
 */
package exceptions;

/**
 * @author Steve Brown
 *
 */
public class NullDriverException extends Exception{
	private static final long serialVersionUID = 1L;

	public NullDriverException(String errMsg) {
		super(errMsg);
	}
}
