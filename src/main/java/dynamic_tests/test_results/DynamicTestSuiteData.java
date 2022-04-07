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
public class DynamicTestSuiteData {
//	private String testType;
//	private String elementName;
//	private TestElementDetails details;
//	private String expectedResult;
//	private String actualResult;
	
	private final String testSuiteName;	

	public DynamicTestSuiteData(final MenuItem item) {

		this.testSuiteName = item.getName();
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

//	public String getElementName() {
//		return details.getName();
//	}
//
//	public String getExpectedResult() {
//		return expectedResult;
//	}
//
//	public String getActualResult() {
//		return actualResult;
//	}
//
//	public String getElementType() {
//		return testType;
//	}
//	
//	public DynamicTestData setElementName(String elementName) {
//		this.elementName = elementName;
//		return this;
//	}
//
//	public DynamicTestData setExpectedResult(String expectedResult) {
//		this.expectedResult = expectedResult;
//		return this;
//	}
//
	public DynamicTestSuiteData setActualResult(String actualResult) {
//		this.actualResult = actualResult;
		return this;
	}
//
//	public DynamicTestData setTestType(String testType) {
//		this.testType = testType;
//		return this;
//	}
		
}
