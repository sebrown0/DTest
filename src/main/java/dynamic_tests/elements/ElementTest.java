/**
 * 
 */
package dynamic_tests.elements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import dynamic_tests.factories.ClazzFactory;
import dynamic_tests.finders.MethodGetter;
import object_models.pages.homepage.CoreData;
import site_mapper.jaxb.menu_items.MenuItem;
import utils.StringUtil;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Pass the container that has the control test.
 * @since 1.0
 */
public abstract class ElementTest implements TestElement{
	protected MenuItem item;
	
	private String name;
	private String type;
	private ControlTest controlTest;
	private List<DynamicTest> tests = new ArrayList<>();

	public ElementTest(MenuItem item, String type, String name, ControlTest controlTest) {
		this.item = item;
		this.controlTest = controlTest;
		this.type = type;
		this.name = name;
	}

	public List<DynamicTest> getTests() {
		return tests;
	}
	
	@Override //TestElement
	public String getName() {
		return name;
	}
	@Override //TestElement
	public String getType() {
		return type;
	}
	
	protected ControlTest getControlTest() {
		return controlTest;
	}
	
	//can the item be passed????????????????????????????????????????????
	protected void addTestMethod(String methodType, CoreData coreData) {
		String methodName = methodType + StringUtil.capitiliseFirstChar(name);
		Method m = 
				((MethodGetter) controlTest)
				.getMethodsWithTypeAndName(methodType, methodName);
		
		Optional
			.ofNullable(ClazzFactory.getClazz(m, coreData))
			.ifPresentOrElse(
					c -> {
						try {							
							DynamicTest test = (DynamicTest)m.invoke(c);
							tests.add(test);							
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							new MethodError(methodName).run();
						}
					}, 
					new MethodError(methodName)
			);		
	}
	
	private class MethodError implements Runnable {
		String methodName;
		
		public MethodError(String methodName) {
			this.methodName = methodName;
		}

		@Override
		public void run() {
			LogManager
				.getLogger(ElementTest.class)
				.error(
						String.format(
								"Error setting dynamic test method, for [%s.%s]", 
								item.getClassName(), methodName
						)
				);
		}		
	}
	
}
