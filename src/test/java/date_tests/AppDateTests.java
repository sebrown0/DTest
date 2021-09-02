package date_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import object_models.helpers.AppDates;

class AppDateTests {

	@Test
	void convertAppDate_toDatePickerDate() {
		String dateForPicker = AppDates.getAsDatePicker("22/05/2021");
		assertEquals("22 May 2021", dateForPicker);	  
	}

}
