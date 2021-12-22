/**
 * 
 */
package site_mapper.finders;

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
	
	public static Class<?> getClazz(ElementClass nodeClass){		
		try {
			return Class.forName(getPathToClass(nodeClass));
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("Could not get class for [" + nodeClass + "]");
		}
		return null;
	}
	
	public static String getPathToClass(ElementClass nodeClass) {
		return getPathInLowerCase(nodeClass) + "." + nodeClass.getClassName();
	}
	public static String getPathInLowerCase(ElementClass nodeClass) {
		String path = 
				ROOT + "." +
				"module_" + nodeClass.getModuleName() + "." + 
				nodeClass.getParentPackage() + "." + 
				nodeClass.getPackage();
		
		return path.toLowerCase();
	}
	
}