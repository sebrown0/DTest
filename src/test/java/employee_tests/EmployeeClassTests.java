/**
 * 
 */
package employee_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dto.Employee;
import dto.EmployeeOptional;
import dto.EmployeeRequired;
import enums.EmploymentType;
import enums.Gender;
import providers.employee.EmployeeFromXml;
import providers.employee.EmployeeProvider;
import providers.employee.RandomEmployeeProvider;

/**
 * /**
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 */
class EmployeeClassTests {

	@Test
	void simpleEmployee() {
		EmployeeRequired emp = new Employee();
		emp.setFirstName("Steve").setDateOfBirth("2000-01-22");
		assertEquals("Steve", emp.getFirstName());
	}	
	@Test
	void empOne_fromXml() {
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		Employee emp = empProvider.getEmployeeWithRequiredFields("1");
		assertTrue(emp.getFullName().length() >= 1);
	}
	@Test
	void withSomeRequiredFields() {
		EmployeeRequired emp = new Employee(); 
				emp
				.setFirstName("Steve")
				.setLastName("Brown")
				.setGender(Gender.MALE);
		assertEquals(Gender.MALE, emp.getGender());
	}
	@Test
	void withSomeRequiredFields_and_OptionalFields() {
		EmployeeRequired empReq = new Employee(); 
		empReq
			.setFirstName("Steve")
			.setLastName("Brown")
			.setGender(Gender.MALE);
				
		EmployeeOptional empOpt = (EmployeeOptional) empReq;
		empOpt.setEmploymentType(EmploymentType.FULL_TIME);
		
		Employee emp = (Employee) empOpt;
		assertEquals(Gender.MALE, emp.getGender());
		assertEquals(EmploymentType.FULL_TIME, emp.getEmploymentType());
	}
	@Test
	void empFromXml_withRandomCode() {
		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
		Employee emp = empProvider.getAnyEmpWithRandomCode();
		assertTrue(emp.getEmpCode().length() == 10);
	}
//	@Test
//	void withInvalid_empTitle() {
//		EmployeeOptional empOpt = new Employee(); 
//		empOpt.setEmployeeTitle("INVALID");
//				
//		
//		Employee emp = (Employee) empOpt;
//		assertEquals(Gender.MALE, emp.getGender());
//		assertEquals(EmploymentType.FULL_TIME, emp.getEmploymentType());
//	}
}
