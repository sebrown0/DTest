package listners;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestResultLogger implements TestWatcher, AfterAllCallback {
	private List<TestResultStatus> testResultsStatus = new ArrayList<>();
	
  private enum TestResultStatus {
    SUCCESSFUL, ABORTED, FAILED, DISABLED;
  }
  
  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {
      System.out.printf("%nTest Disabled for test {%s}: with reason :- {%s}", 
        context.getDisplayName(),
        reason.orElse("No reason"));

      testResultsStatus.add(TestResultStatus.DISABLED);
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
  	System.out.printf("%nTest Successful for test {%s}: ", context.getDisplayName());
    testResultsStatus.add(TestResultStatus.SUCCESSFUL);
  } 
  
  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {
  	System.out.printf("%nTest Aborted for test {%s}: ", context.getDisplayName());
    testResultsStatus.add(TestResultStatus.ABORTED);
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
  	System.out.printf("%nTest {%s} Failed for test {%s}: ", context.getTags(), context.getDisplayName());
    testResultsStatus.add(TestResultStatus.FAILED);
  }
  
	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
