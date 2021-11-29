/**
 * 
 */
package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class PayPeriod {
	private int currentPeriodNum;
	private DateTimeFormatter dtf;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public PayPeriod(int currentPeriodNum, LocalDate startDate, LocalDate endDate) {
		this.currentPeriodNum = currentPeriodNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dtf = DateTimeFormatter.ofPattern("dd MMM u", Locale.ENGLISH);
	}

	public int getCurrentPeriodNum() {
		return currentPeriodNum;
	}
	public String getPayPeriodDateWithPeriodNum() {
		return String.valueOf(currentPeriodNum) + " - " + getLongPayPeriodDate();
	}
	public String getLongPayPeriodDate() {		
		String date = "";
		try {
			date = startDate.format(dtf) + " to " + endDate.format(dtf);	
		} catch (Exception e) {
			
		}		
		return date;
	}	
	
}
