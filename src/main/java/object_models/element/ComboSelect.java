/**
 * 
 */
package object_models.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.Control;
import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;

/**
 * @author Steve Brown
 *
 */
public class ComboSelect implements Control {
	private WebElement combo;
	
	public ComboSelect(WebElement element) {
		this.combo = element;
	}
	
	public ComboSelect(WebDriver driver, By findBy) {
		this.combo = driver.findElement(findBy);
	}
	
	public void click() {
		combo.click();
	}
	
	public String getTextByValue() {
		String txt = "";
		try {
			txt = combo.getAttribute("value").trim();			
		} catch (Exception e) {	
			//Nothing			
		}
		return txt;
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
//		String txt = "";
//		
////		System.out.println("1->" + combo.getAttribute("class")); // TODO - remove or log
//		
//		try {
//			txt = combo.getText();
//			if(txt.length() > 1) {
//				return txt.trim();	
//			}			
//		} catch (Exception e) {
//		}
//		
//		try {
//			txt = combo.getAttribute("title");
//			if(txt.length() > 1) {
//				return txt.trim();	
//			}			
//		} catch (Exception e) {
//		}
//		
//		try {
//			txt = combo.getAttribute("value");
//			if(txt.length() > 1) {
//				return txt.trim();	
//			}
//		} catch (Exception e) {
//		}
//		
//		try {
//			txt = combo.getAttribute("textContent");
//			if(txt.length() > 1) {
//				return txt.trim();	
//			}
//		} catch (Exception e) {
//		}
//		
////		try {
////			List<WebElement> children = combo.findElements(By.xpath("./child::*"));
////			for (WebElement e : children) {
////				System.out.println("2->" + e.getAttribute("textContent")); // TODO - remove or log 	
////				System.out.println("3->" + e.getAttribute("class")); // TODO - remove or log
////			}
////		}catch (Exception e) {
////		}
//		return txt;
	}
	
	public void writeText(String txt) {
		combo.clear();
		combo.sendKeys(txt);
	}
}
