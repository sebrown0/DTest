/**
 * 
 */
package object_model_tests;

import java.lang.reflect.Method;

/**
 * @author SteveBrown
 *
 */
public interface TestClass {
	Method[] getMethods();
//	void setConfig(ConfigReader configReader);
	void tearDown();
}
