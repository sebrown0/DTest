/**
 * 
 */
package object_models.helpers;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Steve Brown
 *
 */
public class IFrame {
	private WebDriver driver;
	private WebElement iFrame;
	private By byLocator;
		
	public IFrame(WebDriver driver, By byLocator) {
		this.driver = driver;
		this.byLocator = byLocator;
	}
		
	public IFrame switchUsingLocator() {
		
		/*
		 * 
		 */
		try {
			WebElement e = driver.findElement(By.id("corners"));
			System.out.println("B4 3->" + e.getText()); // TODO - remove or log	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/*
		 * IN iFRAME OF EMP DETAILS.
		 * THIS HAS MODAL HEADER OF EMPLOYEES
		 */
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		iFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));	
		driver.switchTo().frame(iFrame);
		
		return this;
	}
		
	public WebElement getIFrameElement() {
		return iFrame;
	}
}
