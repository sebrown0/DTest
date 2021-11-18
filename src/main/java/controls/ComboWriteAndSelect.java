/**
 * 
 */
package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import object_models.dk_grid.Popup;
import object_models.helpers.text_utils.RemoveX;
import object_models.helpers.text_utils.TextExtractor;
import object_models.helpers.text_utils.TextSanitiser;
import object_models.helpers.text_writer.TextWriter;

/**
 * @author Steve Brown
 *
 */
public class ComboWriteAndSelect extends ComboSelectFromList implements Popup {
	private TextWriter writer;
	
	public ComboWriteAndSelect(WebDriver driver, By findBy, By resultsBy, TextWriter writer) {
		super(driver, findBy, resultsBy);
		
		this.writer = writer;
	}
	
	public String getAllText() {	
		return combo.getText();
	}
	
	public String getDefaultText() {
		TextExtractor txtExt = new TextExtractor(combo, new RemoveX());
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	public String getText(TextSanitiser sanitiser) {
		TextExtractor txtExt = new TextExtractor(combo, sanitiser);
		return txtExt.getFirstOccurenceOfTextFromElement();
	}
	
	public void writeText(String txt) {
		writer.writeText(txt);
	}
	
}
