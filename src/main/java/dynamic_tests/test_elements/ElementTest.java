/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.mappers.TestNode;
import dynamic_tests.test_adders.TestAdder;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Pass the container that has the control test.
 * @since 1.0
 * 
 * Is a bridge between the Element(SiteMapper -> POM) 
 * and the test(s) for the element.
 */
public class ElementTest implements TestElementDetails {
	private String elName;
	private String type;
	private List<DynamicTest> testList = new ArrayList<>();	
	private ControlFinder controlFinder;
	
	public ElementTest(
		TestNode testNode, HomePage hp, 
		MenuItem item, ElementCreation e) {

		this.type = e.getElementType();
		this.elName = e.getElementName();
		
		controlFinder = new ControlFinder(testNode, hp, item, elName);
	}

	public void addTests(TestAdder testAdder) {
		ElementTestFactory testFactory = 
			new ElementTestFactory(testList, elName, getControl());
		testAdder.addTestsWith(testFactory);
	}
	
	protected Optional<Control> getControl() {
		return controlFinder.getControl();
	}

	@Override //TestElementDetails
	public String getName() {
		return elName;
	}
	@Override //TestElementDetails
	public String getType() {
		return type;
	}
	@Override //TestElementDetails
	public List<DynamicTest> getTestList() {
		return testList;
	}
		
	/*
	 * DON'T DELETE - FOR TEST METHOD.
	 */
//	//can the item be passed????????????????????????????????????????????
//	protected void addTestMethod(String methodType, CoreData coreData) {
//		String methodName = methodType + StringUtil.capitiliseFirstChar(name);
//		Method m = 
//				((MethodGetter) controlTest)
//				.getMethodsWithTypeAndName(methodType, methodName);
//		
//		Optional
//			.ofNullable(ClazzFactory.getClazz(m, coreData))
//			.ifPresentOrElse(
//					c -> {
//						try {							
//							DynamicTest test = (DynamicTest)m.invoke(c);
//							tests.add(test);							
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							new MethodError(methodName).run();
//						}
//					}, 
//					new MethodError(methodName)
//			);		
//	}
	
//	private class MethodError implements Runnable {
//		String methodName;
//		
//		public MethodError(String methodName) {
//			this.methodName = methodName;
//		}
//
//		@Override
//		public void run() {
//			LogManager
//				.getLogger(ElementTest.class)
//				.error(
//						String.format(
//								"Error setting dynamic test method, for [%s.%s]", 
//								item.getClassName(), methodName
//						)
//				);
//		}		
//	}
	
}
