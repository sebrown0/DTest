package object_models.element;

import org.openqa.selenium.WebElement;

public final class ElementButton extends Element{
	public ElementButton(WebElement e) {
		super(e);
	}

	public void click() {
		super.getElement().click();
	}
		
}