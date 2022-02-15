/**
 * 
 */
package controls;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Add isAvailable().
 * @version 1.2
 * 	Add getFaFaText().
 * @since 1.0
 *
 */
public class Button implements Control, HasToolTip, HasFaFa, DisplayedText {
	private WebDriver driver;
	private By btnLocator;
	private WebElement btn;
	
	public Button(WebElement btn) {
		this.btn = btn;
	}
	
	public Button(WebDriver driver, By btnLocator) {
		this.driver = driver;
		this.btnLocator = btnLocator;
	}

	public boolean click() {		
		if(isAvailable()) {
			btn.click();
			return true;
		}
		return false;
	}

	@Override //HasToolTip
	public String getToolTipText() {
		String res = "** NOT FOUND **";
		if(isAvailable()) {
			res = getTipFromTitle(btn, 2);
		}
		LogManager.getLogger(this.getClass()).error("Tool tip not found");
		return res;
	}
	
	/**
	 * @param el: the current element (btn is the root).
	 * @param attempts: number of attempts to find title from parent.
	 * @return the title if found.
	 * 
	 * Go through the button's parents recursively until the
	 * title attribute is found, for n attempts.
	 */
	private String getTipFromTitle(WebElement el, int attempts) {
		WebElement prnt = el.findElement(By.xpath(".."));		
		var title = prnt.getAttribute("title");
		if(title != null && title.length()>0) {
			return title;				
		}				
		return getTipFromTitle(prnt, --attempts);
	}
	
	@Override //HasFaFa
	public String getFaFaText() {
		String strFaFa = "";
		
		if(isAvailable()) {			
			if(currentElementIsFaFaClass()) {
				strFaFa = btn.getAttribute("class");
			}else if(currentElementContainsFaFaClass()) {
				strFaFa = btn.findElement(By.tagName("i")).getAttribute("class");					
			}				
		}
		return strFaFa;	
	}
	
	private boolean currentElementIsFaFaClass() {
		String checkFaFa = btn.getAttribute("class");
		return isFaFa(checkFaFa);
	}
	private boolean currentElementContainsFaFaClass() {
		WebElement elmntFaFa = btn.findElement(By.tagName("i"));
		if(elmntFaFa != null ) {
			return isFaFa(elmntFaFa.getAttribute("class"));
		}
		return false;
	}
	private boolean isFaFa(String checkFaFaStr) {
		boolean res = false;
		if(checkFaFaStr != null && checkFaFaStr.startsWith("fa")) {
			res = true;
		}
		return res;
	}
	
	@Override //DisplayedText
	public String getText() {
		if(isAvailable()) {
			return btn.getText().trim();	
		}else {
			return null;
		}		
	}
	
	@Override //Control
	public boolean isAvailable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			btn = wait.until(ExpectedConditions.elementToBeClickable(btnLocator));
			return true;
		} catch (Exception e) {
			LogManager.getLogger().error("Unable to find btn [" + e + "]");
			return false;
		}		
	}
	
}
