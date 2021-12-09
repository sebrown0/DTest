/**
 * 
 */
package site_mapper.class_finder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;

import site_mapper.NodeClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ClassFinder {
	private static String ROOT = "object_models";
	
	public static Class<?> getClazz(NodeClass nodeClass){		
		String fullPath = ROOT + "." + getPath(nodeClass) + nodeClass.getClassName();
		try {
			return Class.forName(fullPath);
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("Could not get class for [" + nodeClass + "]");
		}
		return null;
	}
	
	private static String getPath(NodeClass nodeClass) {
		String[] parts = nodeClass.getPath().split(Pattern.quote("."));
		String path = "";
		
		for(int i = 0; i < parts.length - 1; i++) {
			path += parts[i] + ".";
		}
		return path;
	}

	public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
    final List<Method> methods = new ArrayList<Method>();
    Class<?> klass = type;
   
    for (final Method method : klass.getDeclaredMethods()) {
        if (method.isAnnotationPresent(annotation)) {
            // Annotation annotInstance = method.getAnnotation(annotation);
            // TODO process annotInstance
            methods.add(method);
        }
    }
   
    return methods;
	}

}
