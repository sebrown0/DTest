/**
 * 
 */
package test_result;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.extension.ExtensionContext;

import tags.TagParser;
import tags.TestCaseTag;
import testrail_api.TestCaseData;

/**
 * @author Steve Brown
 *
 */	
public class TestResult implements TestResultGetter {
	private TestCaseData data = new TestCaseData();
	private ExtensionContext context;
	
	public TestResult(TestResultSetter resultSetter, ExtensionContext context) {
		this.context = context;
		setId();
		setName();
		resultSetter.setResult(data);
	}
	
	private void setId() {
		TestCaseTag tag = TagParser.getTestCaseTag(context.getTags());
		
		data
			.setBelongsToTestRunId(tag.getTestRunNum())
			.setTestId(tag.getTestNum());
	}
	
	private void setName() {		
		data			
			.setClassName(context.getTestClass().get().getName())
			.setMethodName(context.getDisplayName());
	}
	
	@Override
	public TestCaseData getResult() {
		return data;
	}

	@Override
	public String getTestIds() {
		return data.getBelongsToTestRunId() + "." + data.getTestId();
	}

	@Override
	public String getTestName() {
		return data.getClassName() + "." + data.getMethodName();
	}
	
	@Override
	public String getTestComments() {
		return data.getComment();
	}
	
	@Override
	public Level getLogLevel() {		
		return data.getLogLevel();
	}
	
//	public TestResult() {	}

//
//	/*
//	 * The result is in the data.
//	 */
//	public TestResult(TestCaseData data) {		
//		this.data = data;
//	}
//	
//	/*
//	 * The result has been partly set. The TestResultSetter finishes the job.
//	 */
//	public TestResult(TestResultSetter resultSetter, TestCaseData data) {		
//		this.data = data;
////		resultSetter.setResult(data);
//	}
//	
//	/*
//	 * Set the result from the context of the test.
//	 */
//	public TestResult(TestResultSetter resultSetter, ExtensionContext context) {
//		setDataUsingContext(context);
////		resultSetter.setResult(data);
//	}
//		
	
}
