package test_result;

import org.apache.logging.log4j.Level;

import testrail_api.TestCaseData;

/**
 * @author Steve Brown
 *
 * 	Get abstracted data values for the test result. 
 */	
public interface TestResultGetter {
	TestCaseData getResult();
	
	Level getLogLevel();
	String getTestIds();
	String getTestName();
	String getTestComments();
}
