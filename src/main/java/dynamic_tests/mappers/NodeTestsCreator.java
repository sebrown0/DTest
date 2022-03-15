/**
 * 
 */
package dynamic_tests.mappers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.finders.ClassFinder;
import dynamic_tests.finders.MethodFinder;
import dynamic_tests.test_elements.DynamicTestFactory;
import dynamic_tests.test_elements.ElementTest;
import dynamic_tests.test_elements.ElementTestFactory;
import dynamic_tests.test_elements.TestElementDetails;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 * 
 * Add the specific tests for each 
 * test node in the item, 
 * i.e. all the tests for a button in an input group.
 */
public class NodeTestsCreator {

	private List<TestNode> testNodes;
	private IncludedElements includedElements;
	private MenuItem item;;
	private HomePage hp;
	private List<DynamicContainer> menuItemTests;
	
	private DynamicTestFactory testFactory = new DynamicTestFactory();
	
	public NodeTestsCreator(
		List<TestNode> testNodes, IncludedElements includedElements, 
		MenuItem item, HomePage hp,	List<DynamicContainer> menuItemTests) {
		
		this.testNodes = testNodes;
		this.includedElements = includedElements;
		this.item = item;
		this.hp = hp;
		this.menuItemTests = menuItemTests;
	}

	public NodeTestsCreator addElementTestsForEachTestNode(ElementTestFactory tf) {
		testNodes.forEach(tn -> { 
			
			addContainerFunctionTest(tn); 	
			addTestsForElements(tn, tf);
			
		});	
		return this;
	}
	
//	private void addContainerFunctionTest(TestNode tn) {
//		ElementFunction f = tn.getFunc();
//		if(f != null) {
//			System.out.println(tn.getName() + "\n" + f.toString()); // TODO - remove or log	
//			menuItemTests.add(
//				DynamicContainer.dynamicContainer(
//					"Functions", 
//					List.of(DynamicTest.dynamicTest("Is [" + f.getName() +"] FUNCTION correct?", 
//						() -> {	fail("!! NOT IMPLEMENTED !!"); })))				
//					);					
//		}				
//	}

	private void addContainerFunctionTest(TestNode tn) {
		ElementFunction f = tn.getFunc();
		if(f != null) {
			System.out.println(tn.getName() + "\n" + f.toString()); // TODO - remove or log
			Object obj = ClassFinder.getClazz(item);
//			Class clazz = 
			List<DynamicTest> tests = new ArrayList<>();
			
			List<Method> methods = MethodFinder.getTestMethodsOfType(obj.getClass(), "CONTAINER");
			if(methods != null) {
				for(Method m : methods) {
					tests.add(getTest(null, m));	
				}				
			}
			
			
			menuItemTests.add(
				DynamicContainer.dynamicContainer(
					"Functions", 
					tests				
					));					
		}				
	}
	
	private DynamicTest getTest(Class<?> clazz, Method m) {
		try {
			return (DynamicTest) m.invoke(clazz);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	private void addContainerFunctionTest(TestNode tn) {
//		ElementFunction f = tn.getFunc();
//		if(f != null) {
//			System.out.println(tn.getName() + "\n" + f.toString()); // TODO - remove or log	
//			menuItemTests.add(
//					MethodFinder.getTestMethodsOfType(tn.getClass(), "button"))					
//		}				
//	}
	
		
	private void addTestsForElements(TestNode tn, ElementTestFactory tf) {
		List<Element> els = tn.getElements();
		if(els != null) {
			els.forEach(el -> addTestForElement(tn, el, tf));							
		}			
	}
	
	private void addTestForElement(TestNode tn, Element el, ElementTestFactory tf) {
		if(includedElements.isIncluded(el.getElementType())) {
			ElementTest elTest = new ElementTest(tn, hp, item, el, tf);
			testFactory.addElementSpecificTestsTo(elTest, el);
			addTestToItemContainer(elTest);
		}
	}
	
	private void addTestToItemContainer(ElementTest t) {		
		menuItemTests.add(
				DynamicContainer.dynamicContainer(getKey(t), t.getTestList()));		
	}
		
	private String getKey(TestElementDetails e) {
		return e.getType() + "." + e.getName();
	}
}
