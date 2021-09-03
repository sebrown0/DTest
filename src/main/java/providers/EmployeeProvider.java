/**
 * 
 */
package providers;

import dto.Employee;

/**
 * @author Steve Brown
 *
 * Get a record (recordNum) from the relevant source.
 */
public interface EmployeeProvider {
	Employee getEmployeeAll(String recordNum);
	Employee getEmployeeRequired(String recordNum);
}
