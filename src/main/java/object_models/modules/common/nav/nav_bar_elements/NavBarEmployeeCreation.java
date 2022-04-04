package object_models.modules.common.nav.nav_bar_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import object_models.helpers.Closable;
import object_models.pages.homepage.CoreData;
import object_models.modules.common.nav.NavBarElement;
import object_models.employee_creation.EmployeeCreationWizard;

/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 04/04/2022 16:22:30
*/
public class NavBarEmployeeCreation extends NavBarElement {
	public static final String ORIGINAL_NAME = "Employee Creation";

	public NavBarEmployeeCreation(CoreData coreData) {
		super(coreData, ORIGINAL_NAME);
	}

	@Override
	public Closable clickElement() {
		WebElement el = 
			super.getNavBar().findElement(By.xpath(".//li/a/i[contains(@class, 'fa fa-plus')]"));
		el.click();
		return new EmployeeCreationWizard(coreData);
	}

}