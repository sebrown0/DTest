/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import dynamic_tests.factories.ClazzFactory;
import dynamic_tests.finders.MethodFinder;
import object_models.modules.payroll.left_menu.employees.EmployeeDetails;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.CoreDataLoader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ClassFactoryTests {
	private static final String CANONICAL_NAME = 
	"object_models.modules.payroll.left_menu.employees.EmployeeDetails";
	
	private static final String CLASS_NAME = "EmployeeDetails";
	private static final String METHOD_TYPE = "button";
	private static final String METHOD_NAME = "buttonSave";
	
	private static final CoreData cd = new CoreDataLoader(null); 
	
	/*
	 * HAVE TO USE A FULLY INSTANTIATED CoreData!!!!!
	 */
	
	@Test
	void get_JsPanelWithIFrame_fromClassName() {		
		Object clazz = ClazzFactory.getClazz(CANONICAL_NAME, cd);
		
		assertEquals(CLASS_NAME, clazz.getClass().getSimpleName());
	}

	@Test
	void get_JsPanelWithIFrame_fromMethod() {
		Method m = MethodFinder.getTestMethodOfTypeWithName(EmployeeDetails.class, METHOD_TYPE, METHOD_NAME);
		Object clazz = ClazzFactory.getClazz(m, cd);
		
		assertEquals(CLASS_NAME, clazz.getClass().getSimpleName());
	}
	
}
