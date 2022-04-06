/**
 * 
 */
package dynamic_tests.test_results;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestFail implements DynamicTestResult {
	
	@Override
	public void addTestData(DynamicTestData data) {
		System.out.println("DynamicTestFail"); // TODO - remove or log
	}
	
}
