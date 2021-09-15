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
	 * 	[0] module
	 * 	[1] include test file (XML)
	 * 
	 **/
	public static void main(String[] args) {
		StartUp startUp = new StartUp(args);
		startUp.start().runTests().finish();		
	}
	
}
