/**
 * 
 */
package utils;

/**
 * @author Steve Brown
 *
 */
public class StringUtil {
	public static String capitiliseFirstChar(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}
	public static String getValueAt(String s, int pos, String delimitter) {
		String[] elements = s.split(delimitter);
		return elements[pos].replace("'", "");
	}
	public static String removeLastChar(String s) {		
		if(s != null && s.length() > 1) {
			return s.substring(0, s.length()-1);
		}else {
			return s;
		}		
	}
}
