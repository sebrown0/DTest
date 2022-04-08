/**
 * 
 */
package controls.with_text;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controls.data.InsertItem;
import controls.interfaces.ControlTest;
import dynamic_tests.test_elements.DataInserter;
import dynamic_tests.test_elements.DataInserterItem;
import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TextOut extends ControlWithText implements InsertItem {
	private ControlTest controlTest;
	
	public TextOut(WebDriver driver, By locator, ControlTest controlTest) {
		super(driver, locator);
		
		this.controlTest = controlTest;
	}

	@Override
	public void insert(TestDataItem item) {
		DataInserter inserter = new DataInserterItem(item, controlTest, this);
		inserter.insertData();
	}
	@Override
	public void insert(String value) {
		if(isAvailable()) {
			elContainer.sendKeys(value);
		}
	}
}
