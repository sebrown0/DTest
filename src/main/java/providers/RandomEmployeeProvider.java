/**
 * 
 */
package providers;

import dto.Employee;

/**
 * @author SteveBrown
 *
 */
public interface RandomEmployeeProvider {
	Employee getAnyEmpWithRandomCode();
}
