/**
 * 
 */
package object_models.left_nav_bar;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextManager;
import entities.Company;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */

public class LeftNavBar {
	private WebDriver driver;
	private WebElement appHeader;
	private Company currentCompany;
	@SuppressWarnings("unused")
	private ContextManager contextManager;
	private Logger logger = LogManager.getLogger();
	
	public LeftNavBar(WebDriver driver, ContextManager contextManager) {
		this.driver = driver;
		this.contextManager = contextManager;
		setHeader();
	}
	
	private void setHeader() {
		appHeader = driver.findElement(By.cssSelector("header[class='app-header navbar']"));
	}
	
	public Company getCompany() {
		if(currentCompany == null) {
			currentCompany = new Company(getActualCompanyName());
		}
		return currentCompany;
	}
	
	public String getActualCompanyName() {
		WebElement e = appHeader.findElement(By.cssSelector("ul[class='nav mr-auto']"));
		return e.findElement(By.cssSelector("button[id='fourboot_']")).getText();
	}
	
	/*
	 * Load a company by clicking the drop down list of companies.
	 * If the required company is not found in the list then
	 * the existing company is returned.
	 */
	public Company loadCompany(String companyName) {				
		setHeader();
		if(companyName != null && companyName != "") {
			WebElement nav = appHeader.findElement(By.cssSelector("ul[class='nav mr-auto']"));
			nav.findElement(By.cssSelector("button[id='fourboot_']")).click();
			List<WebElement> companies = nav.findElements(By.cssSelector("button[class^='dropdown-item  ']"));
			for (WebElement comp : companies) {
				String name = comp.getText();
				if(companyName.equalsIgnoreCase(name)) {
					logger.info("Loading company [" + name + "]");
					comp.click();			
					currentCompany = new Company(companyName);				
					break;
				}				
			}
		}
		return currentCompany;		
	}
	
}
