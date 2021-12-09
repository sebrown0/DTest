/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import site_mapper.Module;
import site_mapper.Node;
import site_mapper.NodeTest;
import site_mapper.SiteMapper;

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
		
		Node empDetails = payroll.getNode("LM.Employees.Employee Details");
		assertTrue(empDetails != null);
	}
	
	@TestFactory
	DynamicNode payrollModule() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		
		NodeTest empDetails = payroll.getNode("LM.Employees.Employee Details");
		return empDetails.getTests();
	}
//	@TestFactory
//	Collection<DynamicTest> getTestsFor_EmployeeDetails() {
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules();
//		
//		Map<String, Module> modules = mapper.getModuleMap();
//		Module payroll = modules.get("Payroll");
//		
//		Node empDetails = payroll.getNode("LM.Employees.Employee Details");
//		return empDetails.getNodeTests();
//	}
}
