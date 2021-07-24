package tags;

import java.util.Set;

/**
 * @author Steve Brown
 * @Comment
 * 
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
				tagObj.setTestCaseNum(s);
			}
		}		
		return tagObj;
	}

	public static String getTestRunNum(String tag) {
		 return getNumberForType('R', tag);
	}
	
	public static String getTestCaseNum(String tag) {
		 return getNumberForType('C', tag);
	}
	
	private static char getType(String tag) {
		return tag.charAt(0);
	}
	
	private static String getNumberForType(char type, String tag) {
		return tag.charAt(0) == type ? tag.substring(1, tag.length()) : "0";
	}
}
