package object_models.element;

import org.openqa.selenium.By;

public class Button {
	private By locator;
	private String btnText;
	
	@SuppressWarnings("unused")
	private Button() {}
	
	public Button(By locator, String btnText) {
		this.locator = locator;
		this.btnText = btnText;
	}
	
	public By getLocator() {
		return locator;
	}
	public String getBtnText() {
		return btnText;
	}
	
}
