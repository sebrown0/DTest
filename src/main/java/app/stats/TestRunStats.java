/**
 * 
 */
package app.stats;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class TestRunStats {
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
	private String startTime;
	private String endTime;
	
	public void setStartTime() {
		startTime = LocalTime.now().format(dtf);
	}
	public void setEndTime() {
		endTime = LocalTime.now().format(dtf);
	}
	
	public String getEndTime() {
		return endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	
}
