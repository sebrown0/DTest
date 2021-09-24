package object_models.element;

import org.openqa.selenium.WebElement;

public final class ElementInput {
	private WebElement element;
	
	public ElementInput(WebElement e) {
		this.element = e;
	}

	public void inputText(String txt) {
		element.sendKeys(txt);
	}
}
