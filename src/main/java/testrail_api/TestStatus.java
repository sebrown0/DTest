/**
 * 
 */
package testrail_api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 * @Comment
 * Status of a test as specified by the TR API.
 * The status must be in the range specified in TestStatusValues. * 
 */
public final class TestStatus {// implements TestLogValue {	
	private int status;
	
	@SuppressWarnings("unused")
	private TestStatus() {}
	
	public TestStatus(int status) {
		checkAndSetStatus(status);		
	}
	
	public void setStatus(int status){
		checkAndSetStatus(status);		
	}
		
	private void checkAndSetStatus(int status) {
		if(TestStatusValues.isValidStatus(status)) {
			this.status = status;
		}else {
			Logger logger = LogManager.getLogger(this.getClass());
			logger.error("[" + status +  "] is not a correct test status");
			this.status = -1;
		}
	}
	public int getStatus() {
		return status;
	}
	
}
