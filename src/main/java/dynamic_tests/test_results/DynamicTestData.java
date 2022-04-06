/**
 * 
 */
package dynamic_tests.test_results;

import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestData {	
	private String testName;
	private DynamicTestResult result;
	
	private final String testSuiteName;	

	public DynamicTestData(final MenuItem menuItem) {
		this.testSuiteName = menuItem.getClassName();
	}

	public DynamicTestResult getResult() {
		return result;
	}

	public void setResult(DynamicTestResult result) {
		this.result = result;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}
		
}
