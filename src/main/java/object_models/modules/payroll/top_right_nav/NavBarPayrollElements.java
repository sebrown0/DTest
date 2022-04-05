package object_models.modules.payroll.top_right_nav;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.WebDriver;
import object_models.modules.common.nav.NavBarElement;
import object_models.pages.homepage.CoreData;
import object_models.modules.common.nav.nav_bar_elements.NavBarElementStrategy;
import object_models.modules.common.nav.quick_links.QuickLinks;
import object_models.modules.common.nav.quick_links.QuickLinksPayroll;

import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 05/04/2022 15:51:13
*/

public class NavBarPayrollElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;
	private WebDriver driver;
	private CoreData coreData;

	public NavBarPayrollElements(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		setElements();
	}

	private void setElements(){
		elements = Stream.of(new Object[][] {{
			NavBarEmployeeCreation.ORIGINAL_NAME, new NavBarEmployeeCreation(coreData)}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1])); 
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}

	@Override
	public QuickLinks getQuickLinks() {
		return new QuickLinksPayroll(driver);
	}
}