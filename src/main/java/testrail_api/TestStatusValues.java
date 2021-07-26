package testrail_api;

/**
 * @author Steve Brown
 * @Comment
 * Values given & expected by the TR API for a test.
 * Use this every time we need a test status.
 *
 */
public final class TestStatusValues {
	
	/*
	 * Use like an enum.
	 */
	public static int PASSED() { return 1; }
	public static int BLOCKED() { return 2; }
	public static int UNTESTED() { return 3; }
	public static int RETEST() { return 4; }
	public static int FAILED() { return 5; }
	
	public static boolean isValidStatus(int status) {
		final boolean isValid = status >= 1 && status <= 5 
			  ? true 
			  : false;
		return isValid;
	}
}
