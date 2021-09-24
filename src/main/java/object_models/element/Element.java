package object_models.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Element {
	private WebElement element;	
	private String elmntText = "NONE";
	private String elmntTitle = "NONE";
	private String elmntFaFaName = "NONE";
	private String elmntKey = "NONE";
	
	public Element(WebElement e) {
		this.element = e;
		setAttributes();
	}
	
	private void setAttributes() {
		setKey();
		setTitle();
		setFaFaName();
	}
	
	private void setKey() {
		try {
			elmntKey = element.getText().trim();
		} catch (NoSuchElementException e) {
			//Nothing
		}	
	}
	
	private void setTitle() {
		try {
			elmntTitle = element.getAttribute("title").trim();
		} catch (NoSuchElementException e) {
			//Nothing
		}	
		addAsKeyIfAbsent(elmntTitle);
	}
		
	private void setFaFaName() {
		try {
			elmntFaFaName = element.findElement(By.tagName("span")).getAttribute("class").trim();
		} catch (NoSuchElementException e) {								
			try {
				elmntFaFaName = element.findElement(By.tagName("i")).getAttribute("class").trim();	
//				System.out.println("->trying for fafa:" + btnFaFaName);
			} catch (NoSuchElementException e2) {	
				//Nothing
			}
		}		
		addAsKeyIfAbsent(elmntFaFaName);
	}

	private void addAsKeyIfAbsent(String potentialKey) {
		if(elmntKey.equalsIgnoreCase("None") || elmntKey.equalsIgnoreCase("") || elmntKey.length() <= 0) {
			elmntKey = potentialKey;
		}		
	}
		
	// Getters
	public WebElement getElement() {
		return element;
	}
	public String getElementText() {
		return elmntText;
	}
	public String getElementTitle() {
		return elmntTitle;
	}
	public String getElementFaFaName() {
		return elmntFaFaName;
	}
	public String getElementKey() {
		return elmntKey;
	}

	// Setters
	public void setElementKey(String btnKey) {
		this.elmntKey = btnKey;
	}
	
	@Override
	public String toString() {
		return "Element [Text=" + elmntText + ", Title=" + elmntTitle + ", FaFaName=" + elmntFaFaName + ", Key="
				+ elmntKey + "]";
	}

		
}