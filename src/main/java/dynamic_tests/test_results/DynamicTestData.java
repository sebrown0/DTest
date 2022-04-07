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
	private String elementName;
	private String expectedResult;
	private String actualResult;
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

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public String getActualResult() {
		return actualResult;
	}

	public DynamicTestData setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
		return this;
	}

	public DynamicTestData setActualResult(String actualResult) {
		this.actualResult = actualResult;
		return this;
	}
		
}
