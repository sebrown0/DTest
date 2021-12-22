/**
 * 
 */
package site_mapper.finders;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import site_mapper.annotations.TestControl;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class MethodFinder {
	public static List<Method> getMethodsAnnotatedWith(final Class<?> clazz, final Class<? extends Annotation> annotation) {
    final List<Method> methods = new ArrayList<Method>();   
    for (final Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(annotation)) {
        methods.add(method);
      }
    }   
    return methods;
	}
	
	public static List<Method> getTestMethodsOfType(final Class<?> clazz, final String type) {
    final List<Method> methods = new ArrayList<Method>();   
    for (final Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(TestControl.class)) {
      	TestControl ann = method.getAnnotation(TestControl.class);
      	if(ann.type().equals(type)) {
      		methods.add(method);
      	}            
      }
    }   
    return methods;
	}
}
