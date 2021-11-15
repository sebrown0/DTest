/**
 * 
 */
package factories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import control_builder.ControlData;
import control_builder.ControlGetterButton;
import control_builder.ControlGetterComboWriteAndSelect;
import control_builder.ControlGetterGrid;
import controls.ControlName;
import enums.control_names.CommonControlNames;
import object_models.dk_grid.KeyStrategyRow;
import object_models.helpers.text_writer.TextWriter;

/**
 * @author Steve Brown
 *
 * Build and get a ControlData obj that can be 
 * used to build a panel's (or similar) controls.
 */
public class ControlDataFactory {
	private WebDriver driver;
	private ControlData controlData;
	
	public ControlDataFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	public <T extends KeyStrategyRow> ControlDataFactory buildGrid(T keyStrategy) {
		controlData = 
				new ControlData(
						CommonControlNames.DK_GRID, 
						new ControlGetterGrid<T>(driver, keyStrategy));		
		
		return this;
	}
	
	public ControlDataFactory buildButton(ControlName cntrlName, By containerLoc) {
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterButton(driver, containerLoc));
		
		return this;
	}
	
	public ControlDataFactory buildComboWriteAndSelect(ControlName cntrlName, By containerLoc, By resultLoc, TextWriter writer) {
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterComboWriteAndSelect(driver,	containerLoc,	resultLoc, writer));
		
		return this;
	}
	
	public ControlData getControlData() {
		return controlData;
	}
	
}
