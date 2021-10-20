/**
 * 
 */
package object_models.forms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextManager;
import context_manager.ContextState;
import context_manager.states.StateModalForm;
import object_models.helpers.ButtonClicker;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.Title;

/**
 * @author Steve Brown
 *
 */
public class FormFadeShow extends FormModal {
	WebDriverWait wait;
	WebElement frm;
	
	public FormFadeShow(WebDriver driver, ContextManager contextManager) {
		super(driver, contextManager);
		
//		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	@Override
	protected void waitForLoad() {		
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		frm = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.cssSelector("div[class='modal fade show']")));
	}

	@Override
	protected void setContextStateToPanel() {
		ContextState con = contextManager.getCurrentContext();
		con.setState(new StateModalForm(con));
	}
	
	@Override
	public PageTitle getTitle() {
		return new Title("", driver, By.className("modal-body"));
	}
	
//	@Override
//	public void close() {
////		WebDriverWait btnWait = new WebDriverWait(driver, Duration.ofSeconds(3));
////		By btnLocator = By.className("close");
////		WebElement btn = frm.findElement(btnLocator);
////		btnWait.until(ExpectedConditions.elementToBeClickable(btnLocator));
////		btn.click();
////				//frm.findElement(btnLocator);
//		
//		WebElement btn = frm.findElement(By.className("close"));
////		WebElement btn = frm.findElement(By.cssSelector("button[class='btn btn-secondary']"));
//		System.out.println("Close->FormFadeShow -> " + btn.getText()); // TODO - remove or log
//		btn.click();
//		
////		contextManager.closeCurrentContext();
//	}
	
	@Override
	public void close() {
		ButtonClicker.clickUntilNotVisible(driver, By.className("close"), 25);
//		By btnLocator = By.className("close");		
//		int control = 0;
//		while(ExpectedConditions.visibilityOfElementLocated(btnLocator) != null && ++control < 100) {
//			WebElement btn = frm.findElement(btnLocator);
//			System.out.println("Close->FormFadeShow -> " + btn.getText()); // TODO - remove or log
//			btn.click();
//		}
//		contextManager.closeCurrentContext();
	}
	
	private WebElement getCloseBtn() {
		return frm.findElement(By.className("close"));
	}

}
