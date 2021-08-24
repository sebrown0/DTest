package listeners;

import java.util.Optional;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import providers.XMLFileProvider;
import reporting.strategy.ResultWriter;
import test_result.ResultDisabled;
import test_result.ResultFailed;
import test_result.ResultPassed;
import test_result.TestResult;
import xml_reader.ConfigReader;

/**
 * @author SteveBrown
 * 
 * A test watcher that updates the results.
 * 
 * @params
 * ConfigReader: reads the config file.
 * ResultWriter: writes the result to a specified place, i.e. console, log etc. 
 */
public class TestResultLogger implements TestWatcher, BeforeAllCallback {
	private ResultWriter resultWriter;
	private ConfigReader configReader;
	
	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		resultWriter = configReader.getResultWriter(context.getDisplayName());		
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
  		
//@Override
//public void testAborted(ExtensionContext context, Throwable cause) {
//	System.out.printf("%nTest Aborted for test {%s}: ", context.getDisplayName());
//  testResultsStatus.add(TestResultStatus.ABORTED);
//}

}
