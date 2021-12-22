/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;

import object_models.module_payroll.left_menu.employees.EmployeeDetails;
import site_mapper.annotations.TestControl;
import site_mapper.finders.MethodFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class MethodGetterTests {

	@Test
	void getAllMethods() {		
		List<Method> methods = MethodFinder.getMethodsAnnotatedWith(AnnotatedClass.class, TestControl.class);
		assertTrue(methods.size()==3);
	}
	
	@Test
	void getMethodsThatAreButtons() {		
		List<Method> methods = MethodFinder.getTestMethodsOfType(AnnotatedClass.class, "button");
		assertEquals(2, methods.size());
	}
	
	@Test
	void getMethodsThatAreButtons_fromEmpDetails() {		
		List<Method> methods = MethodFinder.getTestMethodsOfType(EmployeeDetails.class, "button");
		assertTrue(methods.size()>0);
	}
	
	@Test
	void getButtonSave_fromEmpDetails() {		
		AnnotatedClass clazz = new AnnotatedClass();
		String s=null;
		List<Method> methods = MethodFinder.getTestMethodsOfType(clazz.getClass(), "button");
		Method m = methods.get(0);
		try {
			s = (String) m.invoke(clazz);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals("buttonSave", s);
	}
	
	class AnnotatedClass{
		@TestControl(type = "button")
		public String buttonSave() {
			return "buttonSave";
		}
		@TestControl(type = "button")
		public String buttonClear() {
			return "buttonClear";
		}
		@TestControl(type = "text_out")
		public String testEmpCode() {
			return "text_out";
		}
	}

}
