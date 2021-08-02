/**
 * 
 */
package testrail_api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Brown
 * @Comment
 * The data in a test case from or to the API.
 */
public class TestCaseData implements TestCaseDataGetter{
	private String belongsToTestRunId;
	private String testId;
	private TestStatus status;
	private String comment;
	private String name;
		
	public TestCaseData() {}
	
	public TestCaseData(String belongsToTestRunId, String testId) {
		super();
		this.belongsToTestRunId = belongsToTestRunId;
		this.testId = testId;		
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
	
	@Override
	public String toString() {
		return "TestCaseData [belongsToTestRunId=" + belongsToTestRunId + ", testId=" + testId + ", status=" + status.getStatus()
				+ ", comment=" + comment + ", name=" + name + "]";
	}

	public TestCaseData setBelongsToTestRunId(String belongsToTestRunId) {
		this.belongsToTestRunId = belongsToTestRunId;
		return this;
	}	
	public TestCaseData setTestId(String testId) {
		this.testId = testId;
		return this;
	}
	public TestCaseData setStatus(TestStatus status) {
		this.status = status;
		return this;
	}
	public TestCaseData setComment(String comment) {
		this.comment = comment;
		return this;
	}
	public TestCaseData setName(String name) {
		this.name = name;
		return this;
	}
	
	public TestStatus getTestStatus() {
		return status;
	}	
	public String getComment() {
		return comment;
	}
	public String getName() {
		return name;
	}
	public String getTestId() {
		return testId;
	}
	public String getBelongsToTestRunId() {
		return belongsToTestRunId;
	}

}
