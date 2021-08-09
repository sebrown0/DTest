/**
 * 
 */
package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 * <p> 
 * Strips the leading 'javascript:' from the anchor 
 * and excutes the remaing as a js script. 
 * 
 */
public class HrefJavascript implements HrefLink {
	private String anchorText;	
	private JavascriptExecutor js;
	
	public HrefJavascript(String anchorText, WebDriver driver) {
		this.anchorText = stripJavascriptFromText(anchorText);
		js = (JavascriptExecutor) driver;
	}

	private String stripJavascriptFromText(String anchorText) {
		return anchorText.replace("javascript:", "");
	}

	@Override
	public void followLink() {
		js.executeScript(anchorText);		
	}
	
}
