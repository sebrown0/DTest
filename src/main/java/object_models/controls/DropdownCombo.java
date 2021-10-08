/**
 * 
 */
package object_models.controls;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import controls.Control;
import object_models.forms.FormModal;
import object_models.helpers.IFrame;
import object_models.helpers.Reload;

/**
 * @author Steve Brown
 *
 */
@SuppressWarnings("unused")
public class DropdownCombo extends FormModal implements Control {
	private IFrame iFrame;
	private Reload reloadEmpDetails;
	private WebElement topLevelContainer;
	private WebElement table;	
	private By byTopLevelContainer = By.cssSelector("body > form > div.container-fluid");
	
	public static final String MENU_TITLE = "Combos";
	public static final String PANEL_TITLE = "Dropdown Combo";
	
	public DropdownCombo(WebDriver driver, Reload reloadEmpDetails) {
		super(driver, PANEL_TITLE);
		
		this.reloadEmpDetails = reloadEmpDetails;
		switchToIframe();
		loadContainers();
	}
		
	private void switchToIframe() {
		iFrame = new IFrame(driver, By.cssSelector("iframe[name='_iframex-IPORTAL_POPUPS_MEDIUM4']"));
		iFrame.switchUsingLocator();		
	}
	
	private void loadContainers() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byTopLevelContainer));
		topLevelContainer = driver.findElement(byTopLevelContainer);
		table = topLevelContainer.findElement(By.id("myGrid1"));
	}

	/*
	 * Add close method and reload emp details
	 */

}
