package tags;

import java.util.Set;

/**
 * @author Steve Brown
 *  
 * 
 */
public class TagParser {

	public static TestCaseTag getTestCaseTag(Set<String> tags) {
		TestCaseTag tagObj = new TestCaseTag();
		char type;
		
		for (String s : tags) {
			type = getType(s);
			if(type == 'R') {
				tagObj.setTestRunNum(s);
			}else if (type == 'C') {
				tagObj.setTestNum(s);
			}else if (type == 'T') {
				tagObj.setTestNum(s);
			}
//			else if (type == 'N') {
//				tagObj.setTestName(getName(s));
//			}
		}		
		return tagObj;
	}

	public static String getTestRunNum(String tag) {
		 return getNumberForType('R', tag);
	}
	
	public static String getTestCaseNum(String tag) {
		 return getNumberForType('C', tag);
	}
	
	public static String getTestNum(String tag) {
		 return getNumberForType('T', tag);
	}
	
//	private static String getName(String tag) {
//		return tag.substring(2);
//	}
	
	private static char getType(String tag) {
		return tag.charAt(0);
	}
	
	private static String getNumberForType(char type, String tag) {
		return tag.charAt(0) == type ? tag.substring(1, tag.length()) : "0";
	}
}
