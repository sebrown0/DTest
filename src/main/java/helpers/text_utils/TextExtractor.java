/**
 * 
 */
package helpers.text_utils;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 *
 */
public class TextExtractor {
	private String txt = "";
	private WebElement el;
	private TextSanitiser sanitiser;
	private List<String> sanitisedText;
	
	public TextExtractor(WebElement el, TextSanitiser sanitiser) {
		this.el = el;
		this.sanitiser = sanitiser;
	}

	private void sanitiseText() {		
		sanitisedText = sanitiser.sanitiseText(txt);
	}
	
	/*
	 *  Go thru the different methods of getting text from an element.
	 *  When some text is found sanitise it and return it.
	 */
	public String getFirstOccurenceOfTextFromElement() {
		if(getText()) {
			sanitiseText();
			if(sanitisedText.size() > 0) {
				return sanitisedText.get(0);
			}			 
		}
		
		if(getTextByValue()) {
			sanitiseText();
			if(sanitisedText.size() > 0) {
				return sanitisedText.get(0);
			}			 
		}
		
		if(getTextContent()) {
			sanitiseText();
			if(sanitisedText.size() > 0) {
				return sanitisedText.get(0);
			}			 
		}
		
		if(getTextByTitle()) {
			sanitiseText();
			if(sanitisedText.size() > 0) {
				return sanitisedText.get(0);
			}			 
		}
	
// LAST RESORT GO THRU CHILDREN
//try {
//List<WebElement> children = combo.findElements(By.xpath("./child::*"));
//for (WebElement e : children) {
//	System.out.println("2->" + e.getAttribute("textContent")); // TODO - remove or log 	
//	System.out.println("3->" + e.getAttribute("class")); // TODO - remove or log
//}
//}catch (Exception e) {
//}
		
		// If we've got this far no text has been found.
		return txt;
	}
	
	private boolean getText() {
		boolean foundTxt = false;
		try {
			txt = el.getText().trim();
			foundTxt = true;
		} catch (Exception e) {	
			//Nothing			
		}
		return foundTxt;		
	}

	private boolean getTextByValue() {
		return getTextByAttribute("value");
	}
	
	private boolean getTextContent() {		
		return getTextByAttribute("textContent");
	}
	
	private boolean getTextByTitle() {		
		return getTextByAttribute("title");
	}
	
	private boolean getTextByAttribute(String attrName) {
		boolean foundTxt = false;
		try {
			txt = el.getAttribute(attrName).trim();
			foundTxt = true;
		} catch (Exception e) {	
			//Nothing			
		}
		return foundTxt;
	}
}
