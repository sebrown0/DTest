/**
 * 
 */
package providers.employee;

import dto.Employee;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 *
 * Get a record (recordId) from the relevant source.
 */
public interface EmployeeProvider {
	Employee getEmployeeWithAllFields(String recordId);
	Employee getEmployeeWithRequiredFields(String recordId);
}
