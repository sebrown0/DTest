/**
 * 
 */
package employee_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import library.dakar_hr.dto.Employee;
import library.dakar_hr.dto.EmployeeOptional;
import library.dakar_hr.dto.EmployeeRequired;
import library.dakar_hr.enums.EmploymentType;
import library.dakar_hr.enums.Gender;
import library.dakar_hr.providers.employee.EmployeeProvider;
import library.dakar_hr.providers.employee.RandomEmployeeProvider;
import providers.employee.EmployeeFromXml_Old;

/** 
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
		EmployeeProvider empProvider = new EmployeeFromXml_Old(); 
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
		RandomEmployeeProvider empProvider = new EmployeeFromXml_Old(); 
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
