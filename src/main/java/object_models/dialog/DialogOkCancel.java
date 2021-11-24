/**
 * 
 */
package object_models.dialog;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ElementButton;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Given a modal dialog element the elements of
 * the dialog can be retrieved.
 * 
 * NOTE: 
 * 	EACH ELEMENT IS FOUND USING A SPECIFIC LOCATOR.
 * 	IF THE FORM USES DIFFERENT LOCATORS IT WILL NOT WORK!!
 */
public class DialogOkCancel implements Dialog {
	private WebDriver driver;
	private WebElement myContainer;
	private Logger logger = LogManager.getLogger();
	private Optional<String> title;
	private Optional<String> msg;
	private Optional<ElementButton> btnOk;
	private Optional<ElementButton> btnCancel;
	
	public DialogOkCancel(WebDriver driver) {
		this.driver = driver;
		setMyContainer();
		loadElements();
	}
	
	public DialogOkCancel(WebElement modalDialog) {
		this.myContainer = modalDialog;
		loadElements();
	}
	
	private void setMyContainer() {
		myContainer = driver.findElement(By.cssSelector("div[class='modal-dialog']"));
	}

	private void loadElements() {
		setTitle();
		setMsg();
		setOk();
		setCancel();
	}
	
	private void setTitle() {		
		try {
			WebElement e = myContainer.findElement(By.cssSelector("h5[class='modal-title']"));
			title = Optional.ofNullable(e.getText());
		} catch (Exception e) {
			logger.error("Could not get title [" + e + "]");
		}
	}
	
	private void setMsg() {
		try {
			WebElement e = myContainer.findElement(By.cssSelector("div[class='modal-body']"));
			msg = Optional.ofNullable(e.getText());
		} catch (Exception e) {
			logger.error("Could not get message [" + e + "]");
		}
	}
	
	private void setOk() {
		try {
			WebElement e = myContainer.findElement(By.cssSelector("button[class='btn btn-primary']"));
			btnOk = Optional.ofNullable(new ElementButton(e));
		} catch (Exception e) {
			logger.error("Could not get Ok button [" + e + "]");
		}
	}
	
	private void setCancel() {
		try {
			WebElement e = myContainer.findElement(By.cssSelector("button[class='btn btn-secondary']"));
			btnCancel = Optional.ofNullable(new ElementButton(e));
		} catch (Exception e) {
			logger.error("Could not get Cancel button [" + e + "]");
		}
	}
	
	public Optional<String> getTitle() {
		return title;
	}
	public Optional<String> getMsg() {
		return msg;
	}
	public Optional<ElementButton> getBtnOk() {
		return btnOk;
	}
	public Optional<ElementButton> getBtnCancel() {
		return btnCancel;
	}
	
}
