/**
 * 
 */
package object_models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class EmployeeGridView {
	private WebDriver driver;	
	private By byClickLogin = By.cssSelector("[data-original-title='Employee Grid View']");	
	
	public EmployeeGridView(WebDriver driver) {
		this.driver = driver;
		System.out.println("EmployeeGridView");
	}

//	@Override
//	public void clickElement(WebElement webElem) {		
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		System.out.println("Clicking");
//		webElem.findElement(By.className("nav-link")).click();
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		System.out.println("Quitting");
		
//		WebElement clickElem;
//		webElem.click();
//		System.out.println("->" + webElem.findElement(By.className("nav-link")).toString());// .getAttribute("class"));
//		clickElem = wait.until(ExpectedConditions.elementToBeClickable(webElem.findElement(By.className("nav-link"))));
//		clickElem.click();
//		webElem.findElement(By.className("nav-link")).click();
//		System.out.println("->" + driver.findElement(byClickLogin).toString());// .click();
//		System.out.println("->" + driver.findElement(byClickLogin).getTagName());// .click();
//	}

//	@Override
//	public String getOriginalName() {
//		return "Employee Grid View";
//	}
//
//	@Override
//	public By getLocator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
