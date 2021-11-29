/**
 * 
 */
package company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import entities.Company;
import entities.PayGroup;
import entities.PayPeriod;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
class CompanyTests {

	public interface StringConcat{
    String concat(String a, String b);
	}
	
	@Test
	void testPayPeriod_longDate() {
		PayPeriod pp = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		assertEquals("01 Oct 2021 to 31 Oct 2021", pp.getLongPayPeriodDate()); 	
	}

	@Test
	void testPayPeriod_longDate_and_periodNum() {
		PayPeriod pp = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		assertEquals("10 - 01 Oct 2021 to 31 Oct 2021", pp.getPayPeriodDateWithPeriodNum()); 	
	}
	
	@Test
	void testPayGroup() {
		PayPeriod pp = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		PayGroup pg = new PayGroup("Monthly Paygroup", pp);
		assertEquals(pg.getPayGroupName(), "Monthly Paygroup");
	}
	
	@Test
	void testCreateCompany() {		
		Company c = new Company("A Comp");
		assertEquals("A Comp", c.getName());
	}
	
	@Test
	void addTwoPayPeriods_toPayGroup() {
		PayPeriod pp10 = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		PayPeriod pp11 = new PayPeriod(11, LocalDate.of(2021, Month.NOVEMBER, 1), LocalDate.of(2021, Month.NOVEMBER, 30));
		PayGroup pg = new PayGroup("Monthly Paygroup");
		pg.addPayPeriod(pp10);
		pg.addPayPeriod(pp11);
		
		assertEquals(11, pg.getPayPeriod(11).getCurrentPeriodNum());
	}
	
	@Test
	void testAdd_onePayGroup_toNullList() {
		PayPeriod pp = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		PayGroup pg = new PayGroup("Monthly Paygroup", pp);
		Company c = new Company("A Comp");
		c.addPayGroup(pg);
		assertEquals("Monthly Paygroup", c.getPayGroups().get(0).getPayGroupName());
	}
	
	@Test
	void testAdd_twoPayGroups() {
		PayPeriod pp10 = new PayPeriod(10, LocalDate.of(2021, Month.OCTOBER, 1), LocalDate.of(2021, Month.OCTOBER, 31));
		PayPeriod pp11 = new PayPeriod(11, LocalDate.of(2021, Month.NOVEMBER, 1), LocalDate.of(2021, Month.NOVEMBER, 30));
		PayGroup pg1 = new PayGroup("Monthly Paygroup");
		pg1.addPayPeriod(pp10);
		pg1.addPayPeriod(pp11);
		
		PayPeriod pp2 = new PayPeriod(2, LocalDate.of(2021, Month.JANUARY, 1), LocalDate.of(2021, Month.JANUARY, 6));
		PayPeriod pp3 = new PayPeriod(3, LocalDate.of(2021, Month.JANUARY, 7), LocalDate.of(2021, Month.JANUARY, 13));
		PayGroup pg2 = new PayGroup("Weekly Paygroup");
		pg2.addPayPeriod(pp2);
		pg2.addPayPeriod(pp3);
		
		Company c = new Company("A Comp");		
		c.setPayGroups(new ArrayList<>(Arrays.asList(pg1,pg2)));
				
		PayGroup result = c.getPayGroup("Weekly Paygroup");
		assertEquals("Weekly Paygroup", result.getPayGroupName());
		assertEquals("2 - 01 Jan 2021 to 06 Jan 2021", result.getPayPeriod(2).getPayPeriodDateWithPeriodNum());
	}

}
