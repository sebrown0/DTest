/**
 * 
 */
package app.entry_point;

import app.start_up.StartUp;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class Application {
		
	public static void run(String[] args) {		
		StartUp startUp = new StartUp(args);
		startUp.welcome().runTests().finish();
	}
	
	/**
	 * @param args
	 * 	[0] module, i.e. Payroll
	 * 	[1] include test file (XML), i.e. login.xml
	 * 
	 **/
	public static void main(String[] args) {
//		System.out.println("HELLO");
//		TempDynRunner temp = new TempDynRunner();
//		temp.runTests();
		
//		DynamicTestsFromSiteMapperTests app = new DynamicTestsFromSiteMapperTests();
//		app.runTests();
		
//		StartUp startUp = new StartUp(args);
//		startUp.welcome().runTests().finish();
		
		String[] argss = {"Payroll", "login"};
		StartUp startUp = new StartUp(argss);
		startUp.welcome().runTests().finish();
	}
	
}
