/**
 * 
 */
package controls.getters;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import controls.reset.ReloadContainer;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Try different ways to get the text
 * from a control.
 * 
 * If the control is not loaded or stale
 * it's reloaded using ReloadContainer. 
 */
public class TextGetter {
	private WebElement elContainer;
	private int attempts = 0;
	private String res = "";	
	private ReloadContainer reloader;
	
	private static final int MAX_ATTEMPTS = 1;
	
	public TextGetter(WebElement elContainer, ReloadContainer reloader) {
		this.elContainer = elContainer;
		this.reloader = reloader;
	}

	public String getText() {		
		if(attempts <= MAX_ATTEMPTS) {
			attempts++;
			try {
				res = tryToGetTextByDifferentMethods();	
			}catch(StaleElementReferenceException e) {
				elContainer = reloader.reloadContainer();
				getText();
			}			
		}
		return res;
	}
	
	private String tryToGetTextByDifferentMethods() throws StaleElementReferenceException {
		String res = elContainer.getAttribute("innerHTML");
		if(res == null || res.length() <= 0) {
			res = elContainer.getAttribute("value");
		}
		return res;
	}
}
