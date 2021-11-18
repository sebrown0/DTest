/**
 * 
 */
package object_models.dk_grid.buttons;

import org.openqa.selenium.WebDriver;

import context_manager.ContextManager;
import enums.GridButtonNames;
import object_models.element.ElementButton;
import object_models.forms.FormFadeShow;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class SaveChanges extends GridButton {
	public SaveChanges(ElementButton elmntBtn) {
		super(elmntBtn, GridButtonNames.BTN_SAVE);

	}
	
	public FormFadeShow clickButton(WebDriver driver, ContextManager cm) {
		elmntBtn.click();
		return new FormFadeShow(driver, cm);
	}

}
