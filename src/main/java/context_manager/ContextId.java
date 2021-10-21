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
	private ContextMatch matchType;
	private boolean matchFound;
	
	public enum ContextMatch{ 
		NONE,
		STRING_CONTEXT_ID, STRING_EXPECTED, STRING_ACTUAL, 
		OBJ_EXACT, OBJ_CONTEXT_ID, OBJ_EXPECTED, OBJ_ACTUAL 
	}
	
	public ContextId(String expectedName, String actualId) {
		this.expectedName = expectedName;
		this.actualId = actualId;
	}
	
//	public ContextId(String expectedName, Optional<String> actualId) {
//		this.expectedName = expectedName;
//		getIdFromOptional(actualId);
//	}
	
	private void getIdFromOptional(Optional<String> optionalId) {		
		optionalId.ifPresent(id -> actualId = id);
	}
	
	public String getId() {
		return expectedName + ":" + actualId;
	}
	
	public String getExpectedName() {
		return expectedName;
	}
	public String getActualId() {
		return actualId;
	}
		
	@Override
	public boolean equals(Object obj) {
		matchFound = false;
		matchType = ContextMatch.NONE;
		
		if(obj == this) {
			matchType = ContextMatch.OBJ_EXACT;
			matchFound = true;
		}else	if(obj instanceof String) {
			checkForStringMatch((String) obj);
		}else if(obj instanceof ContextId){
			checkForObjectMatch((ContextId) obj);
		}
		return matchFound;		
	}
	
	private void checkForStringMatch(String find) {
		if(getId().equalsIgnoreCase(find)) {
			matchType = ContextMatch.STRING_CONTEXT_ID;
			matchFound = true;
		}
		else if(expectedName.equalsIgnoreCase(find)) {
			matchType = ContextMatch.STRING_EXPECTED;
			matchFound = true;
		}else if (actualId.equalsIgnoreCase(find)) {
			matchType = ContextMatch.STRING_ACTUAL;
			matchFound = true;
		}
	}
	
	// TODO - we don't have case for getContextId() !! 
	private void checkForObjectMatch(ContextId find) {
		if(getId().equalsIgnoreCase(find.getId())){
			matchType = ContextMatch.OBJ_CONTEXT_ID;
			matchFound = true;
		}else if(expectedName.equalsIgnoreCase(find.getExpectedName())){
			matchType = ContextMatch.OBJ_EXPECTED;
			matchFound = true;
		}else if( actualId.equalsIgnoreCase(find.getActualId())) {
			matchType = ContextMatch.OBJ_ACTUAL;
			matchFound = true;
		}
	}
	

	public ContextMatch getMatch() {
		return matchType;
	}

	@Override
	public String toString() {
		return "ContextId [id = " + getId() + ", expectedName=" + expectedName + ", actualId=" + actualId + ", match=" + matchType + "]";
	}

}

