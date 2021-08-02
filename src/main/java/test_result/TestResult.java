/**
 * 
 */
package test_result;

import org.junit.jupiter.api.extension.ExtensionContext;

import tags.TagParser;
import tags.TestCaseTag;
import testrail_api.TestCaseData;

/**
 * @author SteveBrown
 *
 */	
public class TestResult implements TestResultGetter {
	private TestCaseData data;

	/*
	 * The result has been partly set. The TestResultSetter finishes the job.
	 */
	public TestResult(TestResultSetter resultSetter, TestCaseData data) {		
		this.data = data;
		resultSetter.setResult(data);
	}
	
	/*
	 * Set the result from the context of the test.
	 */
	public TestResult(TestResultSetter resultSetter, ExtensionContext context) {
		setDataUsingContext(context);
		resultSetter.setResult(data);
	}
		
	private void setDataUsingContext(ExtensionContext context) {
		TestCaseTag tag = TagParser.getTestCaseTag(context.getTags());
		data = new TestCaseData(tag.getTestRunNum(), tag.getTestNum());
	}
	
	@Override
	public TestCaseData getResult() {
		return data;
	}

}
