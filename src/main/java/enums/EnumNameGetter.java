/**
 * 
 */
package enums;

/**
 * @author Steve Brown
 *
 * Get the name of an enum field.
 */
public class EnumNameGetter {
	public static <T extends Enum<T>> String getName(T e) {
		if(e == null) {
			return "";
		}else {
			return e.name();
		}
	}	
}
