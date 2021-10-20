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
	
	@Override
	public void close() {
		System.out.println("Close->FormFadeShow"); // TODO - remove or log 	
		WebElement btn = frm.findElement(By.className("close"));
		btn.click();
		contextManager.closeCurrentContext();
//		CloserModalForm closer = new CloserModalForm(driver);
//		try {
//			closer.close();
//		} catch (Exception e) {
////			logger.error("Could not close form [" + title.getExpected() + "]");
//		}		
	}

}
