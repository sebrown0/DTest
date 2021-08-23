package listeners;

import java.util.Optional;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import providers.XMLFileProvider;
import reporting.strategy.ResultWriter;
import test_result.ResultDisabled;
import test_result.ResultFailed;
import test_result.ResultPassed;
import test_result.TestResult;
import xml_reader.ConfigReader;

public class TestResultLogger implements TestWatcher, AfterAllCallback {
	private ResultWriter resultWriter;
	private ConfigReader configReader;
	
	public TestResultLogger() {
		super();
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		resultWriter = configReader.getResultWriter();
	}
		
	@Override
  public void testSuccessful(ExtensionContext context) {
		resultWriter.writeResult(new TestResult(new ResultPassed(), context));
  } 
  
  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
		resultWriter.writeResult(new TestResult(new ResultFailed(cause), context));		
  }
  
	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {
		resultWriter.writeResult(new TestResult(new ResultDisabled(reason), context));
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




}
