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
	private int periodNum;
	private boolean isCurrentPayPeriod;
	private DateTimeFormatter dtf;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public PayPeriod(int periodNum, boolean isCurrentPayPeriod, LocalDate startDate, LocalDate endDate) {
		this.periodNum = periodNum;
		this.isCurrentPayPeriod = isCurrentPayPeriod;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dtf = DateTimeFormatter.ofPattern("dd MMM u", Locale.ENGLISH);
	}

	public int getPeriodNum() {
		return periodNum;
	}
	public String getPayPeriodDateWithPeriodNum() {
		return String.valueOf(periodNum) + " - " + getLongPayPeriodDate();
	}
	public String getLongPayPeriodDate() {		
		String date = "";
		try {
			date = startDate.format(dtf) + " to " + endDate.format(dtf);	
		} catch (Exception e) {
			
		}		
		return date;
	}	

	public boolean isCurrentPayPeriod() {
		return isCurrentPayPeriod;
	}

	public void setCurrentPayPeriod(boolean isCurrentPayPeriod) {
		this.isCurrentPayPeriod = isCurrentPayPeriod;
	}
	
}
