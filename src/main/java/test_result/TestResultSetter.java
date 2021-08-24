package test_result;

import testrail_api.TestCaseData;

/**
 * @author Steve Brown
 *
 * TestResult uses this to set TestCaseData for the 
 * different test result types. 
 */	
public interface TestResultSetter {
	void setResult(TestCaseData data);	
}
