package object_models.left_menu.payroll;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import object_models.dialog.Dialog;
import object_models.dialog.DialogOkCancel;
import object_models.element.ComboSelect;
import object_models.forms.FormWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormWithIFrame {	
	private WebElement container;
	private WebDriverWait waitForMsg;
	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";	
	
	public InitialisePayroll(WebDriver driver, ContextManager contextManager) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT", contextManager);
		
		super.switchToIFrame();		
		setMyContainers();
//		container = driver.findElement(By.cssSelector("body > form > div"));
		waitForMsg = new WebDriverWait(driver, Duration.ofSeconds(20));
	}		
	
	// Actions
	public Dialog clickInitialisePayroll() {
		container
			.findElement(By.cssSelector("body > form > div > div:nth-child(9) > div:nth-child(4) > div.btn.btn-warning"))
			.click();
		return new DialogOkCancel(driver.findElement(By.cssSelector("div[class='modal-dialog']")));
	}
	
	public Optional<String> getPayrollInitialisedMsg() {
		waitForMsg.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > form > div > div:nth-child(8) > div")));
		return Optional.ofNullable(driver.findElement(By.cssSelector("body > form > div > div:nth-child(8) > div")).getText());
	}
	
	public Optional<String> getPayrollAlreadyInitialisedMsg() {		
		waitForMsg.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > form > div > div:nth-child(9) > div.col-md-12 > div")));
		return Optional.ofNullable(driver.findElement(By.cssSelector("body > form > div > div:nth-child(9) > div.col-md-12 > div")).getText());
	}
	
	// Elements
	public ComboSelect getSelectCompany() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(3) > div:nth-child(2) > select")));		
	}
	public ComboSelect getSelectPayGroup() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(4) > div:nth-child(2) > select")));		
	}	
	public ComboSelect getSelectPayPeriod() {
		return new ComboSelect(container.findElement(By.cssSelector("div:nth-child(5) > div:nth-child(2) > select")));		
	}
//	public ElementButton getInitialisePayroll() {
//		return new ElementButton(container.findElement(By.cssSelector("body > form > div > div:nth-child(9) > div:nth-child(4) > div.btn.btn-warning")));
//	}
		
	@Override
	public void setMyContainers() {
		container = driver.findElement(By.cssSelector("body > form > div"));
	}

}