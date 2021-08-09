/**
 * 
 */
package object_models.panels;

import static providers.PageTitleProvider.EMPLOYEE_LIST_TITLE;

import org.openqa.selenium.WebDriver;

import object_models.pages.Page;

/**
 * @author SteveBrown
 *
 */
public class EmployeeList extends Page {

	public EmployeeList(WebDriver driver) {
		super(driver, EMPLOYEE_LIST_TITLE);
	}


}
