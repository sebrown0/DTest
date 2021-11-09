/**
 * 
 */
package factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import control_builder.ControlData;
import control_builder.ControlGetterComboWriteAndSelect;
import enums.control_names.CommonControlNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import enums.control_names.PayrollControlNames;
import object_models.helpers.text_writer.TextWriterComboDefault;
import object_models.helpers.text_writer.TextWriterComboMulti;

/**
 * @author SteveBrown
 *
 */
public class ControlDataFactory {
	private WebDriver driver;
	private ControlData controlData;
	
	public ControlDataFactory(WebDriver driver) {
		this.driver = driver;
	}

	public ControlDataFactory buildCompanyComboData() {
		controlData = new ControlData(
				CommonControlNames.COMPANY,
					new ControlGetterComboWriteAndSelect(
							driver, 
							By.cssSelector("span[aria-labelledby='select2-COMP_SELx-container']"), 
							By.id("select2-COMP_SELx-results"), 
							new TextWriterComboDefault(driver)));
		return this;
	}
	
	public ControlDataFactory buildPayPeriodComboData() {
		controlData = new ControlData(
				PayrollControlNames.PAY_PERIODS,
				new ControlGetterComboWriteAndSelect(
						driver, 
						By.cssSelector("span[aria-labelledby='select2-PAYS_SELx-container']"), 
						By.id("select2-PAYS_SELx-results"), 
						new TextWriterComboDefault(driver)));
		return this;
	}

	public ControlDataFactory buildDepartmentComboData() {
		By containerLocator = By.cssSelector("body > form > div:nth-child(17) > div:nth-child(2) > span > span.selection > span"); 
		controlData = new ControlData(
				CommonControlNames.DEPARTMENT,
				new ControlGetterComboWriteAndSelect(
						driver, 
						containerLocator, 
						By.id("select2-DEPS_SELx-results"), 
						new TextWriterComboMulti(driver.findElement(containerLocator))));
		return this;
	}
	
	public ControlDataFactory buildViewAdjustmentTypeComboData() {
		controlData = new ControlData(
				GlobalAdjustmentControlNames.VIEW_ADJUSTMENT_TYPE,
				new ControlGetterComboWriteAndSelect(
						driver, 
						By.id("select2-VIEW2_SELx-container"), 
						By.id("select2-VIEW2_SELx-results"), 
						new TextWriterComboDefault(driver)));
		
		return this;
	}

	public ControlDataFactory buildPayGroupComboData() {
		controlData = new ControlData(
				PayrollControlNames.PAY_GROUP,
				new ControlGetterComboWriteAndSelect(
						driver, By.id("select2-PAYGS_SELx-container"), 
						By.id("select2-PAYGS_SELx-results"), 
						new TextWriterComboDefault(driver)));
		return this;
	}

	public ControlDataFactory buildFullOrPartTimeComboData() {
		controlData = new ControlData(
				EmployeeControlNames.FULL_OR_PART_TIME,
				new ControlGetterComboWriteAndSelect(
						driver, 
						By.id("select2-EMPL_SELx-container"), 
						By.id("select2-EMPL_SELx-results"), 
						new TextWriterComboDefault(driver)));
		
		return this;
	}
	
	public ControlDataFactory buildEmployeesComboData() {
		By containerLocator = By.cssSelector("body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span");		
		controlData = new ControlData(
				EmployeeControlNames.EMPLOYEES,	
					new ControlGetterComboWriteAndSelect(
							driver, 
							By.cssSelector("body > form > div:nth-child(17) > div:nth-child(4) > span > span.selection > span"), 
							By.id("select2-EMPS_SELx-results"), 
							new TextWriterComboMulti(driver.findElement(containerLocator))));
		
		return this;
	}
	
	public ControlData getData() {
		return controlData;
	}

}
