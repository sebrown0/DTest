/**
 * 
 */
package dynamic_tests.test_results;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestPass implements DynamicTestResult {
	
	@Override
	public void addTestData(DynamicTestData data) {
		System.out.println("DynamicTestPass"); // TODO - remove or log 	
	}
}
