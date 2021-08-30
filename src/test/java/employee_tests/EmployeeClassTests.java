/**
 * 
 */
package employee_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import dto.Employee;
import enums.Gender;

/**
 * @author Steve Brown
 *
 */
class EmployeeClassTests {

	@Test
	void simpleEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("Steve").setDateOfBirth(Date.valueOf("2000-01-22"));
		assertEquals("Steve", emp.getFirstName());
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

}
