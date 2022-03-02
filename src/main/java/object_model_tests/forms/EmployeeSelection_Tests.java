package object_model_tests.forms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import control_builder.control_getters.single.ControlGetterEmployeeSelection;
import controls.button.Button;
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

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Test the different ways of selecting
 * an employee from the employee selection form.
 */
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
	
	@AfterAll
	public static void teardown() {
		hp.close();
	}
	
	@Test
	void test() {
		assertTrue(empSelection != null);
	}

	@Test
	void test_bySurname() {
		SelectEmployee selEmp = empSelection;
		selEmp.UsingSelector(new SelectEmpBySurname(empSelection, "Borg"));
		
		WebElement res = hp.getWebDriver().findElement(By.cssSelector("input[name='XNAME']"));
		assertTrue(res.getAttribute("value").contains("Borg"));
	}
}
