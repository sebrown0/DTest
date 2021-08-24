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
 * 	Create a test result for a test case.
 *  Populate the result depending on the result type.
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
	
}
