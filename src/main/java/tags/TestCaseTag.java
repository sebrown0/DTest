/**
 * 
 */
package tags;

/**
 * @author Steve Brown
 * @Comment
 * The tags that are associated with a test case.
 */
public class TestCaseTag {

	private String testRunNum;
	private String testCaseNum;
	
	public TestCaseTag() {}
	
	public TestCaseTag(String testRunNum, String testCaseNum) {
		super();
		this.testRunNum = testRunNum;
		this.testCaseNum = testCaseNum;
	}
	
	public String getTestRunNum() {
		return testRunNum;
	}
	public void setTestRunNum(String testRunNum) {
		this.testRunNum = testRunNum;
	}
	public String getTestCaseNum() {
		return testCaseNum;
	}
	public void setTestCaseNum(String testCaseNum) {
		this.testCaseNum = testCaseNum;
	}
	
	@Override
	public String toString() {
		return "TestCaseTag [testRunNum=" + testRunNum + ", testCaseNum=" + testCaseNum + "]";
	}
		
}
