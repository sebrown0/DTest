/**
 * 
 */
package dynamic_tests.mappers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.finders.ClassFinder;
import dynamic_tests.finders.MethodFinder;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.ElementFunction;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the dynamic test for a container,
 * if there is one.
 * 
 * An object is created from the data in ElementClass.
 * The object's @TestControl function is found.
 * This returns the dynamic test for the container.
 * The DT is added to the DynamicContainer for the node.
 */
public class ContainerFunctionTest {
	private ElementClass item;
	private List<DynamicContainer> menuItemTests;
	
	private Object obj;
	private Method meth;
	private DynamicTest dt;
	
	public ContainerFunctionTest(ElementClass item, List<DynamicContainer> menuItemTests) {
		this.item = item;
		this.menuItemTests = menuItemTests;
	}
	
	public void addContainerFunctionTest(TestNode tn) {
		ElementFunction f = tn.getFunc();
		if(f != null) {
			getObjectFromMenuItem();
			getMethodFromObject(tn, f);
			if(meth != null) {	
				getDynamicTestFromMethod();				
				if(dt != null) {
					addTestToList();
				}				
			}else {
				LogManager
					.getLogger(ContainerFunctionTest.class)
					.debug(String.format("[%s] has no test function", tn.getName()));
			}		
		}				
	}
	
	private void getObjectFromMenuItem() {
		obj = ClassFinder.getInstantiatedObject(item);
	}
	private void getMethodFromObject(TestNode tn, ElementFunction f) {
		meth = MethodFinder.getTestMethodOfTypeWithName(obj.getClass(), "container", tn.getName()+f.getName());
	}
	private void getDynamicTestFromMethod() {		
		try {
			dt = (DynamicTest) meth.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogManager
				.getLogger(ContainerFunctionTest.class)
				.error(String.format("Error getting DT for method [%s]", meth.getName()));
		}
	}
	private void addTestToList() {
		menuItemTests.add(
				DynamicContainer.dynamicContainer(
					"Functions", 
					Arrays.asList(dt)				
					));		
	}
}
