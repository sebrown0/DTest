/**
 * 
 */
package object_models.forms;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import object_models.helpers.Header;

/**
 * @author Steve Brown
 *
 */
public class FormHeader implements Header {	
	private WebElement header;

	/*
	 * HAS TO SWITCH TO DEFAULT CONTENT!!!!!!!!
	 */
	public FormHeader(WebElement myContainer) {
		header = myContainer.findElement(By.className("modal-header"));
	}
	
	public Optional<String> getTitle() {
		return Optional.ofNullable(header.findElement(By.className("modal-title")).getText());
	}
}
