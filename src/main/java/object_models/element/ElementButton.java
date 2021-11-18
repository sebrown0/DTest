package object_models.element;

import java.util.List;

import org.openqa.selenium.WebElement;

import dto.ButtonData;

/** 
 * @author Steve Brown
 * @since 1.0
 * @version 1.0
 */
public final class ElementButton extends Element implements ElementKey{
	public ElementButton(WebElement e) {
		super(e);
	}

	public void click() {
		super.getElement().click();
	}

	@Override // ElementKey
	public <T extends DataKey> String getKey(List<T> elements) {
		String elmtKey = super.getElementKey();		
		if(isKeyFaFa(elmtKey)) {
			return alternativeKey(elements, elmtKey);
		}
		return elmtKey;
	}
	
	private boolean isKeyFaFa(String elmtKey) {
		return elmtKey.contains("fa") ? true : false;
	}
	
	private <T extends DataKey> String alternativeKey(List<T> elements, String elmtKey) {
		String key = "";		
		ButtonData bd = (ButtonData) elements.stream()
				.filter(b ->  b.getDataKey().equalsIgnoreCase(elmtKey))
				.findAny()
				.orElse(null);
			
			if(bd != null) {
				key = bd.getBtnName();
			}
			return key;			
	}
		
}