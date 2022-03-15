/**
 * 
 */
package dynamic_tests.finders;

import org.apache.logging.log4j.LogManager;

import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Update the way the fullPath is found.
 * @version 1.2
 * 	Remove method finders into separate class.
 * @since 1.0
 */
public class ClassFinder {
	private static String ROOT = "object_models";
	//object_models.module_payroll.left_menu.employees.Banks
	
	//object_models/modules/payroll/left_menu/employees/Banks.java
	//"object_models.payroll.left_menu.employees.Banks" (id=108)	

	public static Class<?> getClazz(ElementClass nodeClass){		
		try {
			String className = getPathToClass(nodeClass);
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("Could not get class for [" + nodeClass + "]");
		}
		return null;
	}
	//object_models.modules.null.left_menu.employees.  package object_models.modules.payroll.left_menu.employees;
	public static String getPathToClass(ElementClass nodeClass) {
		return getPathInLowerCase(nodeClass) + "." + nodeClass.getClassName();
	}
	public static String getPathInLowerCase(ElementClass nodeClass) {
		String path = 
				ROOT + ".modules." +
				nodeClass.getModuleName() + "." + 
				nodeClass.getParentPackage() + "." + 
				nodeClass.getPackage();
		
		return path.toLowerCase();
	}
	
}