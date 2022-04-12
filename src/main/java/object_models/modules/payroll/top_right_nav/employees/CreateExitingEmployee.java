/**
 * 
 */
package object_models.modules.payroll.top_right_nav.employees;

import static org.junit.jupiter.api.Assertions.assertTrue;

import content.EmployeeContent;
import object_models.forms.FormFadeShow;
import object_models.modules.common.nav.NavBarElement;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import providers.XMLFileProvider;
import providers.employee.EmployeeFromXml;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class CreateExitingEmployee implements TestFunction {

	@Override
	public void run(CoreData coreData) {
		HomePage hp = (HomePage) coreData;
		NavBarElement navEmpWizard = 
				hp.getTopRightNavBarElement(
						NavBarEmployeeCreation.ORIGINAL_NAME).get();
		EmployeeFromXml empProvider = 
				new EmployeeFromXml(XMLFileProvider.EMPLOYEE_TESTS_FILE_PATH);
		
		// GET AN EMPLOYEE FROM XML				
		EmployeeContent emp = empProvider.getFirstEmployeeWithCompleteContent();
			
		// GET THE EMPLOYEE WIZARD
		EmployeeCreation	empCreation = (EmployeeCreation) navEmpWizard.clickElement();
		
		// THE EMP EXISTS SO THE RESULT SHOULD BE AN ERROR FORM.
		FormFadeShow frm = 
				empCreation.getEmployeeCreationWizard().createEmployeeAndGetConfirmation(emp);
		
		assertTrue(frm.getTitle().getActual().contains("Data Error"));
		
		//CLOSE THE ERROR FORM AND WIZARD
		frm.close();
		empCreation.close();	
		
	}

}
