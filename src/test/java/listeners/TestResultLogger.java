package listeners;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import exceptions.IncorrectTestStatusException;
import tags.TagParser;
import tags.TestCaseTag;
import testrail_api.MyTestRailAPI;
import testrail_api.TestCaseData;
import testrail_api.TestRailClient;
import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

public class TestResultLogger implements TestWatcher, AfterAllCallback {
//	private List<TestResultStatus> testResultsStatus = new ArrayList<>();

	private MyTestRailAPI api;
	
	public TestResultLogger() {		
		api = new MyTestRailAPI(TestRailClient.getInitialisedClient());
	}
	
	private void updateResult(int status, String msg, ExtensionContext context) {
		TestCaseTag tag = TagParser.getTestCaseTag(context.getTags());
		TestStatus testStatus = null;
		TestCaseData data;

		try {
			testStatus = new TestStatus(status);
		} catch (IncorrectTestStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = new TestCaseData(tag.getTestRunNum(), tag.getTestNum(), testStatus, msg);		
		api.postSingleTest(data);
	}	
  
  @Override
  public void testSuccessful(ExtensionContext context) {
  	updateResult(TestStatusValues.PASSED(), "Test passed", context);  	
//    testResultsStatus.add(TestResultStatus.SUCCESSFUL);
  } 
  
  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {  	
  	updateResult(TestStatusValues.FAILED(), "Test failed", context);
  }
  
	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
//@Override
//public void testAborted(ExtensionContext context, Throwable cause) {
//	System.out.printf("%nTest Aborted for test {%s}: ", context.getDisplayName());
//  testResultsStatus.add(TestResultStatus.ABORTED);
//}
//@Override
//public void testDisabled(ExtensionContext context, Optional<String> reason) {
//    System.out.printf("%nTest Disabled for test {%s}: with reason :- {%s}", 
//    context.getDisplayName(),
//    reason.orElse("No reason"));
//
//  testResultsStatus.add(TestResultStatus.DISABLED);
//}



}
