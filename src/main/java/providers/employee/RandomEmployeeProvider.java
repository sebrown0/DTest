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
 */
public interface RandomEmployeeProvider {
	Employee getAnyEmpWithRandomCode();
}
