/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import object_models.left_menu.employees.EmployeeDetails;
import site_mapper.Module;
import site_mapper.Node;
import site_mapper.NodeTest;
import site_mapper.SiteMapper;
import site_mapper.UiTest;
import site_mapper.class_finder.ClassFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class SiteMapTests {

	@Test
	void mapModules_withoutErrors() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();		
	}
	@Test
	void getPayrollModule() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		assertTrue(payroll != null);
	}
	@Test
	void getEmployeeDetails_fromPayroll() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		
		Node empDetails = payroll.getNode("left_menu.employees.Employee Details");
		assertTrue(empDetails != null);
	}
	
	@Test
	void classForObj() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		
		Node empDetails = payroll.getNode("left_menu.employees.Employee Details");
		Class<?> c = empDetails.getClazz();
		List<Method> methods = ClassFinder.getMethodsAnnotatedWith(c, UiTest.class);
		methods.forEach(m -> System.out.println("->" + m.getName()));
		
		assertTrue(c != null);
	}
	
	@TestFactory
	DynamicNode payrollModule() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		
		NodeTest empDetails = payroll.getNode("left_menu.employees.Employee Details");
		return empDetails.getTests();
	}
	
	@TestFactory
	Collection<DynamicContainer> payrollModule_HHHHHHH() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		return payroll.runTests();
	}
}
