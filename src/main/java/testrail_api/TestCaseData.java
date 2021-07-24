/**
 * 
 */
package testrail_api;

import org.json.simple.JSONObject;

/**
 * @author Brown
 * @Comment
 * The data in a test case from or to the API.
 */
public class TestCaseData implements TestRailPost{
	private String belongsToTestRunId;
	private String testId;
	private TestStatus status;
	private String comment;
	/*
	TODO
	private String version;
	private String elapsedTime;
	private String defects;
	private int assignToId;
	 */
	
	@SuppressWarnings("unused")
	private TestCaseData() {}
	
	public TestCaseData(String belongsToTestRunId, String testId, TestStatus status, String comment) {
		super();
		this.belongsToTestRunId = belongsToTestRunId;
		this.testId = testId;
		this.status = status;
		this.comment = comment;
	}
	
	public String getBelongsToTestRunId() {
		return belongsToTestRunId;
	}	
	public String getTestId() {
		return testId;
	}
	public String getComment() {
		return comment;
	}
	public TestStatus getTestStatus() {
		return status;
	}
	

	@Override
	public String toString() {
		return "TestCaseData [belongsToTestRunId=" + belongsToTestRunId + ", testId=" + testId + ", status=" + status
				+ ", comment=" + comment + "]";
	}

	@Override
	public JSONObject getJSONObject() {
		// TODO Auto-generated method stub
		return null;
	}			
}
