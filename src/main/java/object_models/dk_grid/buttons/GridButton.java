/**
 * 
 */
package object_models.dk_grid.buttons;

import enums.GridButtonNames;
import object_models.element.ElementButton;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public abstract class GridButton {	
	protected ElementButton elmntBtn;
	
	private String faFaName;
	private GridButtonNames btnName;
		
	public GridButton(ElementButton elmntBtn, GridButtonNames btnName) {
		this.elmntBtn = elmntBtn;
		this.btnName = btnName;
		this.faFaName = elmntBtn.getElementFaFaName();
	}
	
	public void clickButton() {
		elmntBtn.click();
	}
	
	public String getFaFaName() {
		return faFaName;
	}

	public GridButtonNames buttonName() {
		return btnName;
	}
		
	public String getKey() {
		return elmntBtn.getElementKey();
	}
}
