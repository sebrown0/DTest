/**
 * 
 */
package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import object_models.LIBRARY.data_picker.DatePickerDay;

/**
 * @author Steve Brown
 *
 */
public class AppDates {
	private static final SimpleDateFormat appFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat datePickerFormat = new SimpleDateFormat(DatePickerDay.DATE_FORMAT);
	private static final Logger logger = LogManager.getLogger();
	
	public static String getAsDatePicker(String appDate) {
		String retDate = null;
		try {
			Date inputDate = appFormat.parse(appDate);			
			retDate = datePickerFormat.format(inputDate);
		} catch (ParseException e) {
			logger.error("Error parsing date string [" + appDate + "]");
		}
		return retDate;
	}
}
