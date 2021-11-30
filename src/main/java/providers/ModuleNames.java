/**
 * 
 */
package providers;

import java.util.Arrays;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ModuleNames {	
	public static final String PAYROLL_NAME = "Payroll";
	public static final String PERSONNEL_NAME = "Personnel";
	public static final String TIME_AND_ATT_NAME = "T&A";
	public static final String ABSENCE_NAME = "Absence";
	public static final String APPRAISAL_NAME = "Appraisal";
	
	
	public static final List<String> ALL_MODULE_NAMES = 
			Arrays.asList(
					PAYROLL_NAME, PERSONNEL_NAME, TIME_AND_ATT_NAME, ABSENCE_NAME, APPRAISAL_NAME
			);
	
	public static boolean isValidName(String name) {
		return ALL_MODULE_NAMES.stream().anyMatch(s -> s.equals(name));
	}
}
