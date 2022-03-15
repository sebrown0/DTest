/**
 * 
 */
package dynamic_tests.mappers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.finders.ClassFinder;
import dynamic_tests.finders.MethodFinder;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * 
 */
public class ElementFunctionTest {
	private ElementClass item;
	private List<DynamicContainer> menuItemTests;
	
	private Object obj;
	private List<Method> methods;
	private DynamicTest dt;
	private List<DynamicTest> tests = new ArrayList<>();
	
	public ElementFunctionTest(ElementClass item, List<DynamicContainer> menuItemTests) {
		this.item = item;
		this.menuItemTests = menuItemTests;
	}
	
	public void addContainerFunctionTest() {		
		getObjectFromMenuItem();
		getMethodsFromObject();
		if(methods != null) {	
			methods.forEach(m -> {
				getDynamicTestFromMethod(m);				
				if(dt != null) {
					tests.add(dt);
				}		
			});
			addTestToList();
		}else {
			LogManager
				.getLogger(ElementFunctionTest.class)
				.debug(String.format("[%s] has no test element functions", item.getName()));
		}								
	}
	
	private void getObjectFromMenuItem() {
		obj = ClassFinder.getInstantiatedObject(item);
	}
	private void getMethodsFromObject() {
		methods = MethodFinder.getTestMethodsOfType(obj.getClass(), "element");
	}
	private void getDynamicTestFromMethod(Method m) {		
		try {
			dt = (DynamicTest) m.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogManager
				.getLogger(ElementFunctionTest.class)
				.error(String.format("Error getting DT for method [%s]", m.getName()));
		}
	}
	private void addTestToList() {
		menuItemTests.add(
				DynamicContainer.dynamicContainer(
					"Element Functions", 	
					tests
					));		
	}
}
