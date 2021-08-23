/**
 * 
 */
package testrail_api;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

/**
 * @author Steve Brown
 * @Comment
 * The data in a test case from or to the API.
 */
public class TestCaseData implements TestCaseDataGetter{
	private String belongsToTestRunId;
	private String testId;	
	private String comment;
	private String name;
	private String className;
	private String methodName;
	private String disabledReason;
	private String failReason;
	private Level logLevel;
	private TestStatus status;
	
	public TestCaseData() {}
	
	public TestCaseData(String belongsToTestRunId, String testId) {
		super();
		this.belongsToTestRunId = belongsToTestRunId;
		this.testId = testId;		
	}
	
	public TestCaseData(String testRundId, String testId, TestStatus status, String comment) {		
		this.belongsToTestRunId = testRundId;
		this.testId = testId;
		this.status = status;
		this.comment = comment;
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
	
//	@Override
//	public String toString() {
//		return "TestCaseData [belongsToTestRunId=" + belongsToTestRunId + ", testId=" + testId + ", status=" + status.getStatus()
//				+ ", comment=" + comment + ", name=" + name + "]";
//	}
		
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
	public TestCaseData setStatusValue(int value) {
		if (this.status == null) {
			this.status = new TestStatus(value);
		}else {
			this.status.setStatus(value);
		}
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
	public TestCaseData setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}	
	public TestCaseData setClassName(String className) {
		this.className = className;
		return this;
	}
	public TestCaseData setDisabledReason(String disabledReason) {
		this.disabledReason = disabledReason;
		return this;
	}
	public TestCaseData setLogLevel(Level level) {
		this.logLevel = level;
		return this;
	}
	public TestCaseData setFailReason(String failReason) {
		this.failReason = failReason;
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
//	public Level getLevel() {
//		return LogLevel.getForStatus(status.getStatus());
//	}
	public String getClassName() {
		return className;
	}
	public String getDisabledReason() {
		return disabledReason;
	}
	public String getMethodName() {
		return methodName;
	}
	public String getFailReason() {
		return failReason;
	}
	public Level getLogLevel() {
		return logLevel;
	}
}
