package testrail_api;

import java.util.Map;

/**
 * @author Steve Brown
 *
 * 	Used in the TestRailAPI only.
 */	
public interface TestCaseDataGetter {
	Map<String, String> getData();
	String testCaseId();
	String testRunId();
}
