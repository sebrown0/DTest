package test_result;

import org.apache.logging.log4j.Level;

import testrail_api.TestCaseData;

public interface TestResultGetter {
	TestCaseData getResult();
	
	Level getLogLevel();
	String getTestIds();
	String getTestName();
	String getTestComments();
}
