/**
 * 
 */
package site_map_tests;

import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import site_mapper.Module;
import site_mapper.ZZZ_SiteMapper;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class })
class SiteMapTests {
	private static HomePage homepagePayroll;	
//	private static EmployeeDetails empDetails;s	
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
//		empDetails =	(EmployeeDetails) homepagePayroll.getLeftMenu().clickAndLoad(EmployeeDetails.class).get();		
	}		
	
//	@Test
//	void mapModules_withoutErrors() {
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules(homepagePayroll);		
//	}
//	@Test
//	void getPayrollModule() {
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules(homepagePayroll);
//		
//		Map<String, Module> modules = mapper.getModuleMap();
//		Module payroll = modules.get("Payroll");
//		assertTrue(payroll != null);
//	}
//	@Test
//	void getEmployeeDetails_fromPayroll() {
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules(homepagePayroll);
//		
//		Map<String, Module> modules = mapper.getModuleMap();
//		Module payroll = modules.get("Payroll");
//		
//		Node empDetails = payroll.getNode("left_menu.employees.Employee Details");
//		assertTrue(empDetails != null);
//	}
//	
//	@Test
//	void classForObj() {
//		
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules(homepagePayroll);
//		
//		Map<String, Module> modules = mapper.getModuleMap();
//		Module payroll = modules.get("Payroll");
//		
//		
//		// Get specific node from XML
//		Node empDetailsNode = payroll.getNode("left_menu.employees.Employee Details");
//		// Get the class for the Node.
//		Class<?> clazzEmpDetails = empDetailsNode.getClazz();
//		// Get al the methods with the annotation in the class.
//		List<Method> methods = ClassFinder.getMethodsAnnotatedWith(clazzEmpDetails, UiTest.class);
//		// Get the specific object for the class. TODO - change the interface to TestType or similar.
//		ContainerAction ed = homepagePayroll.getLeftMenu().clickAndLoad(clazzEmpDetails).get();
//		
//		
//		methods.forEach(m -> { 
//			System.out.println("->" + m.getName());
//			try {
//				m.invoke(ed);
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		);
////		
////		assertTrue(clazzEmpDetails != null);
//	}
//	
//	@TestFactory
//	DynamicNode payrollModule() {
//		SiteMapper mapper = new SiteMapper();
//		mapper.mapModules(homepagePayroll);
//		
//		Map<String, Module> modules = mapper.getModuleMap();
//		Module payroll = modules.get("Payroll");
//		
//		NodeTest empDetails = payroll.getNode("left_menu.employees.Employee Details");
//		return empDetails.getTests();
//	}
	
	@TestFactory
	Collection<DynamicContainer> payrollModule_H() {
		ZZZ_SiteMapper mapper = new ZZZ_SiteMapper();
		mapper.mapModules(homepagePayroll);
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		return payroll.runTests();
	}
	@TestFactory
	Collection<DynamicContainer> payrollModule_HH() {
		ZZZ_SiteMapper mapper = new ZZZ_SiteMapper();
		mapper.mapModules(homepagePayroll);
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		return payroll.runTests();
	}
	@TestFactory
	Collection<DynamicContainer> payrollModule_HHH() {
		ZZZ_SiteMapper mapper = new ZZZ_SiteMapper();
		mapper.mapModules(homepagePayroll);
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		return payroll.runTests();
	}
	
	@TestFactory
	Collection<DynamicContainer> payrollModule_HHHHHHH() {
		ZZZ_SiteMapper mapper = new ZZZ_SiteMapper();
		mapper.mapModules(homepagePayroll);
		
		Map<String, Module> modules = mapper.getModuleMap();
		Module payroll = modules.get("Payroll");
		return payroll.runTests();
	}
	
	/*
	 * 1. WHAT TO DO ABOUT DIFFERENT BUTTONS AT THE BOTTOM OF A PAGE DEPENDING ON THE DATA.
	 * 2. LOAD THE ELEMENT FOR THE NODE ONCE.
	 * 3. FUNCTIONS.
	 * 4. DOES FALL OVER SOMETIMES.
	 */
}
