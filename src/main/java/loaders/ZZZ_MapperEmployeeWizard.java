/**
 * 
 */
package loaders;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import object_models.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public class ZZZ_MapperEmployeeWizard {
	private final WebDriver driver;
	private Map<String, InputWriter> textBoxes;
	private Map<String, InputWriter> comboBoxes;
	
	public ZZZ_MapperEmployeeWizard(WebDriver driver){
		this.driver = driver;
	}
	
	private void mapTextBoxes() {
		ControlMapper mapper = new ControlMapper(driver, null);
		textBoxes = mapper.getControls();
	}
}
