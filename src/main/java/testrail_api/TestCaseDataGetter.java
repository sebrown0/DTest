package testrail_api;

import java.util.Map;

public interface TestCaseDataGetter {
	Map<String, String> getData();
	String testCaseId();
	String testRunId();
}
