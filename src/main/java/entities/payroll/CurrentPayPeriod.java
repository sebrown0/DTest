/**
 * 
 */
package entities.payroll;

import java.time.LocalDate;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class CurrentPayPeriod extends PayPeriod{

	public CurrentPayPeriod(int periodNum, LocalDate startDate, LocalDate endDate) {
		super(periodNum, startDate, endDate);
		super.setAsCurrentPayPeriod();
	}

}
