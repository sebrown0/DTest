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
	private String testType;
	private String elementName;
	private String expectedResult;
	private String actualResult;
	
	private final String testSuiteName;	

	public DynamicTestData(final MenuItem menuItem) {
		this.testSuiteName = menuItem.getClassName();
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public String getElementName() {
		return elementName;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public String getActualResult() {
		return actualResult;
	}

	public String getElementType() {
		return testType;
	}
	
	public DynamicTestData setElementName(String elementName) {
		this.elementName = elementName;
		return this;
	}

	public DynamicTestData setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
		return this;
	}

	public DynamicTestData setActualResult(String actualResult) {
		this.actualResult = actualResult;
		return this;
	}

	public DynamicTestData setTestType(String testType) {
		this.testType = testType;
		return this;
	}
		
}
