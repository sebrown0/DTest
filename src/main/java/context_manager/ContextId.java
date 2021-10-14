/**
 * 
 */
package context_manager;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 * Each context should have an ID.
 * This is the 
 * 	expected name of the context, i.e. "Employee Details".
 * And
 *  the rendered id of the context, i.e jsPanel-1
 *  
 */
public class ContextId {
	private String expectedName;
	private String actualId = null;
	
	public ContextId(String expectedName, String actualId) {
		this.expectedName = expectedName;
		this.actualId = actualId;
	}
	
	public ContextId(String expectedName, Optional<String> actualId) {
		this.expectedName = expectedName;
		getIdFromOptional(actualId);
	}
	
	private void getIdFromOptional(Optional<String> optionalId) {		
		optionalId.ifPresent(id -> actualId = id);
	}
	
	public String getContextId() {
		return expectedName + ":" + actualId;
	}
	
	public String getExpectedName() {
		return expectedName;
	}
	public String getActualId() {
		return actualId;
	}
	
	
}

