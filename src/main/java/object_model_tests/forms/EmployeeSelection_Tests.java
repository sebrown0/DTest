package object_model_tests.forms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import control_builder.control_getters.single.ControlGetterEmployeeSelection;
import controls.Button;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.modal_forms.emp_selection.EmployeeSelection;
import object_models.modal_forms.emp_selection.SelectEmpBySurname;
import object_models.modal_forms.emp_selection.SelectEmployee;
import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class EmployeeSelection_Tests {

	private static SalaryDetails salDetails;
	private static HomePage hp;
	private static EmployeeSelection empSelection;

	@BeforeAll
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
		LeftMenu menu = hp.getLeftMenu();
		salDetails = 
			(SalaryDetails)	menu
				.clickParent("Employees")
				.clickAndLoad(SalaryDetails.class)
				.get();
		
		salDetails
			.getControlFromPanel("EmpLookup", "EmployeeList")
			.ifPresent(c -> { 
				((Button)c).click();
				empSelection = 
						(EmployeeSelection) 
							new ControlGetterEmployeeSelection(salDetails)
								.getControl();
			});		
		
	}
	
	@Test
	void test() {
		assertTrue(empSelection != null);
	}

	@Test
	void askj() {
		SelectEmployee selEmp = empSelection;
		selEmp.UsingSelector(new SelectEmpBySurname(empSelection, "Borg"));
	}
}
