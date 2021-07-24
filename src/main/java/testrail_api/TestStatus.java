/**
 * 
 */
package testrail_api;

import exceptions.IncorrectTestStatusException;

/**
 * @author Steve Brown
 * @Comment
 * Status of a test as specified by the TR API.
 * The status must be in the range specified in TestStatusValues
 * or IncorrectTestStatusException is thrown.
 * 
 * @Throws IncorrectTestStatusException
 */
public final class TestStatus {	
	private int status;
	
	@SuppressWarnings("unused")
	private TestStatus() {}
	
	public TestStatus(int status) throws IncorrectTestStatusException {
		if(TestStatusValues.isValidStatus(status)) {
			this.status = status;
		}else {
			throw new IncorrectTestStatusException(status + ": is not a correct test status");
		}		
	}
	
	public int getStatus() {
		return status;
	}	

}
