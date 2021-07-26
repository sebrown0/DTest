/**
 * 
 */
package testrail_api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brown
 * @Comment
 * The data in a test case from or to the API.
 */
public class TestCaseData implements TestCaseDataGetter{
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
	public Map<String, String> getData() {
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("status_id", String.valueOf(status.getStatus()));
		data.put("comment", comment);
		return data;
	}

	@Override
	public String testCaseId() {
		return testId;
	}

	@Override
	public String testRunId() {
		return belongsToTestRunId;
	}

	
//	public JSONObject singleTest(APIClient client) {
//		Map<String,String> data = new HashMap<String,String>();
//		JSONObject json = null;
//				
//		data.put("status_id", String.valueOf(status.getStatus()));
//		data.put("comment", "This test worked fine!");
//		
//		try {
//			json = (JSONObject) client.sendPost("add_result_for_case/1/1", data);
//		} catch (IOException | APIException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return json;
//	}


	@Override
	public String toString() {
		return "TestCaseData [belongsToTestRunId=" + belongsToTestRunId + ", testId=" + testId + ", status=" + status.getStatus()
				+ ", comment=" + comment + "]";
	}
}
