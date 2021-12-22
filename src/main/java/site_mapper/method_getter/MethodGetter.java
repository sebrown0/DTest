/**
 * 
 */
package site_mapper.method_getter;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface MethodGetter {
	List<Method> getAllTestMethods();
	List<Method> getAllTestMethodsWithType(String type);
}
