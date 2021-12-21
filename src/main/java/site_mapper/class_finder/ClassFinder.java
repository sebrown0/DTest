/**
 * 
 */
package site_mapper.class_finder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import site_mapper.NodeClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Update the way the fullPath is found.
 * @since 1.0
 */
public class ClassFinder {
	private static String ROOT = "object_models";
	
	public static Class<?> getClazz(NodeClass nodeClass){		
//		String fullPath = ROOT + "." + nodeClass.getParentPackage() + "." + nodeClass.getPackage() + "." + nodeClass.getClassName();
		String fullPath = 
				getPathInLowerCase(nodeClass) + "." + 
				nodeClass.getClassName();
		try {
			return Class.forName(fullPath);
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("Could not get class for [" + nodeClass + "]");
		}
		return null;
	}
	
	public static String getPathInLowerCase(NodeClass nodeClass) {
		String path = 
				ROOT + "." +
				"module_" + nodeClass.getModuleName() + "." + 
				nodeClass.getParentPackage() + "." + 
				nodeClass.getPackage();
		
		return path.toLowerCase();
	}
	
	public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
    final List<Method> methods = new ArrayList<Method>();
    Class<?> clazz = type;
   
    for (final Method method : clazz.getDeclaredMethods()) {
        if (method.isAnnotationPresent(annotation)) {
            // Annotation annotInstance = method.getAnnotation(annotation);
            // TODO process annotInstance
            methods.add(method);
        }
    }
   
    return methods;
	}
	
}