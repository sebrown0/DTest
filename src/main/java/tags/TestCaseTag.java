/**
 * 
 */
package tags;

/**
 * @author Steve Brown
 * @Comment
 * 
 * The tags that are associated with a test case. 
 */
public class TestCaseTag {

//	private String testName;
	private String testRunNum;
	private String testNum;			// Either the test or test case num, i.e T123 or C456	
	
	public TestCaseTag() {}
	
	public TestCaseTag(String testRunNum, String testNum) {
		super();
		this.testRunNum = testRunNum;
		this.testNum = testNum;
	}
	
//	public String getTestName() {
//		return testName;
//	}
//	public void setTestName(String testName) {
//		this.testName = testName;
//	}
	public String getTestRunNum() {
		return testRunNum;
	}
	public void setTestRunNum(String testRunNum) {
		this.testRunNum = testRunNum;
	}
	public String getTestNum() {
		return testNum;
	}
	public void setTestNum(String testCaseNum) {
		this.testNum = testCaseNum;
	}
	
	@Override
	public String toString() {
		return "TestCaseTag [testRunNum=" + testRunNum + ", testNum=" + testNum + "]";
	}
		
}
