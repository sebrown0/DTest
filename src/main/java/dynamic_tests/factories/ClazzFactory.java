/**
 * 
 */
package dynamic_tests.factories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dynamic_tests.finders.MethodFinder;
import object_models.module_payroll.left_menu.employees.EmployeeDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get an instance of a class from either:
 * 	1. Enclosing class with method type [i.e. button] and method name [i.e. buttonSave].
 *  2. Method from Enclosing class.
 *  3. From classes canonical name.
 */
public class ClazzFactory {
	
	public static Object getClazz(final Class<?> target, final String methodType, final String methodName) {
		Method m = MethodFinder.getTestMethodOfTypeWithName(EmployeeDetails.class, methodType, methodName);
		
		return getClazz(m);
	}
	
	public static Object getClazz(Method m) {
		Object clazz = null;
		if(m != null) {
			String canonicalName = m.getDeclaringClass().getCanonicalName();
			clazz = getClazz(canonicalName);	
		}	
		
		return clazz;
	}
	
	public static Object getClazz(String canonicalName) {
		Object clazz = null;
		
		try {
			clazz = Class.forName(canonicalName).getConstructor().newInstance();
		} catch (	InstantiationException | IllegalAccessException | 
							IllegalArgumentException | InvocationTargetException |
							NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clazz;
	}

}
