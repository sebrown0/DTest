/**
 * 
 */
package app;

/**
 * @author Steve Brown
 *
 */
public class Application {
		
	/**
	 * @param args
	 * 	[0] module, i.e. Payroll
	 * 	[1] include test file (XML), i.e. login.xml
	 * 
	 **/
	public static void main(String[] args) {
		StartUp startUp = new StartUp(args);
		startUp.welcome().runTests().finish();		
	}
	
}
