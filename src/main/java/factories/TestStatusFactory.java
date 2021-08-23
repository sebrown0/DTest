package factories;

import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

public class TestStatusFactory {
	
	public static TestStatus passed() {
		return getStatus(TestStatusValues.PASSED());
	}
	
	public static TestStatus failed() {
		return getStatus(TestStatusValues.FAILED());
	}
	
	public static TestStatus blocked() {
		return getStatus(TestStatusValues.BLOCKED());
	}
	
	public static TestStatus untested() {
		return getStatus(TestStatusValues.UNTESTED());
	}
	
	public static TestStatus reteset() {
		return getStatus(TestStatusValues.RETEST());
	}
	
	private static TestStatus getStatus(int statusValue) {
		TestStatus status = new TestStatus(statusValue);
		return status;
	}
}
