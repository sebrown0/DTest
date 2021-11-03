/**
 * 
 */
package employee_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dto.Employee;
import enums.Gender;
import providers.EmployeeFromXml;
import providers.EmployeeProvider;
import providers.RandomEmployeeProvider;

/**
 * @author Steve Brown
 *
 */
class EmployeeClassTests {

	@Test
	void simpleEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("Steve").setDateOfBirth("2000-01-22");
		assertEquals("Steve", emp.getFirstName());
	}	
	@Test
	void empOne_fromXml() {
		EmployeeProvider empProvider = new EmployeeFromXml(); 
		Employee emp = empProvider.getEmployeeRequired("1");
		assertTrue(emp.getFullName().length() >= 1);
	}
	@Test
	void withSomeRequiredFields() {
		Employee emp = 
				Employee.withRequiredFields()
				.firstName("Steve")
				.lastName("Brown")
				.gender(Gender.MALE)
				.build();		
		assertEquals(Gender.MALE, emp.getGender());
	}
	@Test
	void empFromXml_withRandomCode() {
		RandomEmployeeProvider empProvider = new EmployeeFromXml(); 
		Employee emp = empProvider.getAnyEmpWithRandomCode();
		assertTrue(emp.getEmpCode().length() == 10);
	}
}
