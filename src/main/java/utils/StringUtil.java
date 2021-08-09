/**
 * 
 */
package utils;

/**
 * @author Steve Brown
 *
 */
public class StringUtil {
	public static String getValueAt(String s, int pos, String delimitter) {
		String[] elements = s.split(delimitter);
		return elements[pos].replace("'", "");
	}
}
