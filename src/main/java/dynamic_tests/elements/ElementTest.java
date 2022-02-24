/**
 * 
 */
package dynamic_tests.elements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicTest;

import control_builder.control_getters.ControlGetter;
import controls.Control;
import controls.ControlGroup;
import controls.ControlTest;
import dynamic_tests.factories.ClazzFactory;
import dynamic_tests.finders.MethodGetter;
import dynamic_tests.mappers.TestNode;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
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
	private MenuItem item;
	private HomePage hp;	
	private String name;
	private String type;
	private ControlTest controlTest;
	private List<DynamicTest> tests = new ArrayList<>();
	private TestNode testNode;
	
	public ElementTest(
		TestNode testNode, HomePage hp, MenuItem item, String type, String name) {
		this.testNode = testNode;
		this.hp = hp;
		this.item = item;
		this.type = type;
		this.name = name;
	}

	public List<DynamicTest> getTests() {
		return tests;
	}
	protected Optional<Control> getControl() {
		Optional<Control> cntrl = null;
		List<String> prntNames = testNode.getPrntNames();
		/*
		 * Load the page and get control
		 */
		ControlTest cntrlTest = myControlTest();
//		Map<String, ControlGetter> controls = cntrlTest.getControls();
//		getGroupForElement(controls);
		//should always be at least one name (the element's node)
		if(prntNames != null) {
			int idx = 0;
			String prntName = prntNames.get(idx);
			ControlGroup grp = (ControlGroup) cntrlTest.getControl(prntName).get();
			while(prntName != null && idx < prntNames.size()-1) {				
				idx++;
				prntName = prntNames.get(idx);
				grp = (ControlGroup) cntrlTest.getControl(prntName).get();				
			}
			cntrl = grp.getControlByTitle(name);			
		}
		return cntrl;
	}
	
//	private ControlGroup getGroupForElement(Map<String, ControlGetter> controls) {
//		ControlGroup grp = null;
//		String cntrlName;
//		for(Entry<String, ControlGetter> entry : controls.entrySet()) {
//			cntrlName = entry.getValue().getName();
//			System.out.println("->" + cntrlName); // TODO - remove or log 	
//		}
//		
//		return grp;
//	}
	private ControlTest myControlTest() {		
		if(controlTest == null) {
			controlTest = getControlTest();
		}
		return controlTest;
	}
	private ControlTest getControlTest() {
		return ElementLoader.getControlTest(item, hp);
	}
	
	@Override //TestElement
	public String getName() {
		return name;
	}
	@Override //TestElement
	public String getType() {
		return type;
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
