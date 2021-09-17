package logging;

import java.util.Optional;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import parameter_resolvers.ConfigParameterResolver;

import org.junit.jupiter.api.extension.TestWatcher;

import reporting.strategy.ResultWriter;
import test_result.ResultDisabled;
import test_result.ResultFailed;
import test_result.ResultPassed;
import test_result.TestResult;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
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
	private String className;
	
	/*
	 * The ConfigReader is loaded by ConfigParameterResolver
	 * and will not be available in this class's beforeAll
	 * method as that is loaded b4 ConfigParameterResolver.
	 * So the first time the ResultWriter is required from
	 * ConfigReader it's loaded from the store.   
	 */
	private ResultWriter getResultWriter(ExtensionContext context) {		
		if(resultWriter == null) {
			configReader = (ConfigReader) context.getStore(Namespace.GLOBAL).get(ConfigParameterResolver.CONFIG_PARAM_ID);
			resultWriter = configReader.getResultWriter(className);		
		}
		return resultWriter;
	}
	
	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		className = context.getDisplayName();
	}
	
	@Override
  public void testSuccessful(ExtensionContext context) {
		getResultWriter(context).writeResult(new TestResult(new ResultPassed(), context));
  } 
  
  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
  	getResultWriter(context).writeResult(new TestResult(new ResultFailed(cause), context));		
  }
  
	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {		
		getResultWriter(context).writeResult(new TestResult(new ResultDisabled(reason), context));
	}

  		
//@Override
//public void testAborted(ExtensionContext context, Throwable cause) {
//	System.out.printf("%nTest Aborted for test {%s}: ", context.getDisplayName());
//  testResultsStatus.add(TestResultStatus.ABORTED);
//}

}
