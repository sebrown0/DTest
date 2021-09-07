/**
 * 
 */
package object_models.panels.menu.employees;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.panels.JSPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class ContactNumbers extends JSPanelWithIFrame {	
	private Tab myTabs;
	
	public static final String PANEL_TITLE ="Employee Contact Details";
	public static final String MENU_TITLE ="Contact Numbers";
			
	public ContactNumbers(WebDriver driver) {		
		super(driver, PANEL_TITLE);
		this.myTabs = new Tab();
	}
		
	// Tabs
	public Tab tab() {
		return this.myTabs;
	}
			
	public class Tab{	
		private WebElement tab;
				
		public Tab contacts() {
			tab = driver.findElement(By.xpath("//a[@href='#tab1']"));
			return this;
		}
		
		public Tab emailDetails() {
			tab = driver.findElement(By.xpath("//a[@href='#tab2']"));
			return this;
		}
				
		public void click() {
			tab.click();			
		}
	}
}
