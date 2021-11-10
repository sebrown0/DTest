package object_models.element;

import java.util.List;

import org.openqa.selenium.WebElement;

import dto.ButtonData;

public final class ElementButton extends Element implements ElementKey{
	public ElementButton(WebElement e) {
		super(e);
	}

	public void click() {
		super.getElement().click();
	}

	@Override // ElementKey
	public String getKey(List<ButtonData> elements) {
		final String elmtKey = super.getElementKey();
		String key = elmtKey;
		
		if(elmtKey.contains("fa")) {
			ButtonData bd = (ButtonData) elements.stream()
				.filter(b ->  b.getBtnFaFa().equalsIgnoreCase(elmtKey))
				.findAny()
				.orElse(null);
			
			if(bd != null) {
				key = bd.getBtnName();
			}
			
		}
		return key;
	}
		
}