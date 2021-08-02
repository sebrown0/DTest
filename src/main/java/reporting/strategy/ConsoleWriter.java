/**
 * 
 */
package reporting.strategy;

/**
 * @author Steve Brown
 *
 */
public class ConsoleWriter implements ResultWriter {

	@Override
	public void writePass() {
		System.out.println("Test passed");
	}

}
