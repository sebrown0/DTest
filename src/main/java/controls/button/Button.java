/**
 * 
 */
package controls.button;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.getters.ElementGetter;
import controls.interfaces.Control;
import controls.interfaces.DisplayedText;
import controls.interfaces.HasFaFa;
import controls.interfaces.HasToolTip;

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
	private By locator;
	private WebElement btn;
	
	public Button(WebDriver driver, By locator, WebElement btn) {
		this.driver = driver;
		this.locator = locator;
		this.btn = btn;
	}
	
	public Button(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
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
	
	private String getTipFromTitle(WebElement el, int attempts) {		
		var title = btn.getAttribute("title");
		if(title != null && title.length()>0) {
			return title;				
		}				
		return "NO TOOL TIP FOUND";
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
		btn = new ElementGetter(driver).getElementIfClickable(this);
		return (btn != null) ? true : false;
	}
	
	@Override //Control
	public By getLocator() {
		return locator;
	}

	@Override //Control
	public WebDriver getDriver() {
		return driver;
	}
	
	@Override //Control
	public WebElement getElement() {
		return btn;
	}
	
	public boolean click() {		
		if(isAvailable()) {
			btn.click();
			return true;
		}
		return false;
	}

	
}
