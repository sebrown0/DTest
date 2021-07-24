package listners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import com.gurock.testrail.APIClient;

import tags.TagParser;
import tags.TestCaseTag;
import testrail_api.TestRail;

public class TestResultLogger implements TestWatcher, AfterAllCallback {
//	private List<TestResultStatus> testResultsStatus = new ArrayList<>();
	TestRail api;
	APIClient client;
	Map<String, String> data;
	
	public TestResultLogger() {
		this.api = new TestRail("sbrown@dakarsoftware.com", "12345");
		this.client = api.getInitialisedClient();
	}
	
  
  @Override
  public void testSuccessful(ExtensionContext context) {
//  	System.out.printf("%nTest Successful for test {%s}: ", context.getDisplayName());
//    testResultsStatus.add(TestResultStatus.SUCCESSFUL);
  } 
  
  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {  	
  	TestCaseTag tagObj = TagParser.getTestCaseTag(context.getTags());
  	System.out.printf("%nTest {%s} Failed for test run {%s}: test case {%s}", context.getDisplayName(), tagObj.getTestRunNum(), tagObj.getTestCaseNum());
//    testResultsStatus.add(TestResultStatus.FAILED);
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
