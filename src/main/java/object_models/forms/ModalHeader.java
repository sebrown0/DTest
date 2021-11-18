/**
 * 
 */
package object_models.forms;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.helpers.Header;

/**
 * @author Steve Brown
 *
 */
public class ModalHeader implements Header {
	private WebElement title;
	private WebElement btnClose;
	
	public ModalHeader(WebElement prntContainer) {		
		setTitleAndCloser(prntContainer);
	}

	private void setTitleAndCloser(WebElement prntContainer) {
		title = prntContainer.findElement(By.className("modal-title"));
		try {			
			btnClose = prntContainer.findElement(By.className("close"));	
		} catch (Exception e) {
			/*
			 * NOT ALL MODAL HEADERS HAVE A CLOSE BUTTON.
			 */
			LogManager.getLogger().debug("Could not set close button");
		}
	}
	
	@Override
	public Optional<String> getTitle() {
		try {
			return Optional.ofNullable(title.getText());	
		} catch (Exception e) {
			LogManager.getLogger().error("Could not get title of modal form");
			return Optional.empty();
		}		
	}
	
	@Override
	public void closeForm() {
		try {
			btnClose.click();
		} catch (Exception e) {
			LogManager.getLogger().error("Error closing modal form");
		}
	}
	
}
