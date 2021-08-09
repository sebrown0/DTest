/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import object_models.navigation.Menu;
import object_models.navigation.MenuItem;
import object_models.panels.EmployeeList;
import utils.ElementUtil;
/**
 * @author SteveBrown
 *
 */
public class LeftMenu {
	private WebDriver driver;
	private JavascriptExecutor js;
	private boolean isMenuBuilt;
	private Menu menu;
	
	public LeftMenu(WebDriver driver) {		
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}
	
	public void buildMenu() {
		String className;		
//		Menu menu = new Menu(parents, children);
		menu = new Menu();
		// SHould be a menu buider!!!!!!!!!!!
		WebElement navAccordion = 
				new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(driver -> driver.findElement(By.id("nav-accordion"))); 
					
		for (WebElement we : ElementUtil.getElementsThatAreChildrenOfElement(navAccordion)) {
			className = we.getAttribute("class");
			if(className.equals("sub-menu dcjq-parent-li")) {						
				menu.getSubElements(we);				
			}else if(className.equals("mobile-leftside-hider")) {				
				WebElement anchor = we.findElement(By.tagName("a"));
				menu.addElement(anchor);
			}	
		}		
		
//		menu.getAllMenuItems().forEach((k,v) -> System.out.printf("\nName: %s - Href: %s", k, v.getHref()));
//		menu.getAllMenuItems().forEach((k,v) -> System.out.printf("\nName: %s", k));		
		isMenuBuilt = true;
	}

	public EmployeeList loadEmployeeList() {
		loadJsPanel("Employee List");
		return new EmployeeList(driver);				
	}

	private void checkMenuBuilt() {
		if(!isMenuBuilt) { buildMenu(); }
	}
	
	private void loadJsPanel(String itemName) {
		String href = null;
		String jsCode;
		MenuItem item;
		
		checkMenuBuilt();		
		item = menu.getMenuItem(itemName);
		if(!(item == null)) {
			href = item.getHref();
			jsCode = href.substring(11, href.length());
			js.executeScript(jsCode);		
		}		
	}
	
}
