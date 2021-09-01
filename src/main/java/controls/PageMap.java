/**
 * 
 */
package controls;

import java.util.HashMap;
import java.util.Map;

import object_models.element.InputWriter;

/**
 * @author Steve Brown
 *
 */
public class PageMap {
	private Map<String, InputWriter> textBoxes = new HashMap<>();
	private Map<String, InputWriter> comboBoxes = new HashMap<>();
	
	public void addTextBox(String key, InputWriter value) {
		if(key != null && key != "") {
			textBoxes.put(key, value);
			System.out.println("adding text box - " + key);
		}
	}
	
	public InputWriter getTextBox(String key) {
		return textBoxes.get(key);
	}
	
	public void addComboBox(String key, InputWriter value) {
		if(key != null && key != "") {
			comboBoxes.put(key, value);
			System.out.println("adding combo box - " + key);
		}
	}
	
	public InputWriter getCombo(String key) {
		return comboBoxes.get(key);
	}
}
