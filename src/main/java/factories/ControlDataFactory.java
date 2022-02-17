/**
 * 
 */
package factories;

import org.openqa.selenium.By;

import context_manager.ContextManager;
import control_builder.ControlGetterButton;
import control_builder.ControlGetterComboSelectOnly;
import control_builder.ControlGetterComboWriteAndSelect;
import control_builder.ControlGetterGrid;
import control_builder.ControlGetterSelect;
import control_builder.ControlGetterTextOut;
import control_builder.control_data.ControlData;
import controls.ControlName;
import enums.control_names.CommonControlNames;
import object_models.dk_grid.KeyStrategyRow;
import object_models.helpers.text_writer.TextWriterComboDefault;
import object_models.helpers.text_writer.TextWriterComboMulti;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Build and get a ControlData obj that can be 
 * used to build a panel's (or similar) controls.
 */
public class ControlDataFactory {
	private CoreData coreData;
	private ControlData controlData;
	
	public ControlDataFactory(CoreData coreData) {
		this.coreData = coreData;
	}
	
	public <T extends KeyStrategyRow> ControlDataFactory buildGrid(T keyStrategy, ContextManager cm) {
		controlData = 
				new ControlData(
						CommonControlNames.DK_GRID, 
						new ControlGetterGrid<T>(coreData, keyStrategy));		
		
		return this;
	}
	
	public ControlDataFactory buildButton(ControlName cntrlName, By containerLoc) {
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterButton(coreData, containerLoc));
		
		return this;
	}
		
	public ControlDataFactory buildComboSelectOnly(ControlName cntrlName, By containerLoc, By resultLoc) {		
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterComboSelectOnly(coreData, containerLoc, resultLoc));
		
		return this;
	}
	
	public ControlDataFactory buildDefaultComboWriteAndSelect(ControlName cntrlName, By containerLoc, By resultLoc) {
		TextWriterComboDefault writer = new TextWriterComboDefault(coreData);
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterComboWriteAndSelect(coreData,containerLoc,	resultLoc, writer));
		
		return this;
	}
	
	public ControlDataFactory buildMultiComboWriteAndSelect(ControlName cntrlName, By containerLoc, By resultLoc) {
		TextWriterComboMulti writer = 
				new TextWriterComboMulti(
							coreData,
							coreData.getWebDriver()
								.findElement(containerLoc)
								.findElement(By.cssSelector("input[class='select2-search__field']")));
		
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterComboWriteAndSelect(coreData, containerLoc,	resultLoc, writer));
		
		return this;
	}
	
	public ControlDataFactory buildTextOut(ControlName cntrlName, By containerLoc) {
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterTextOut(coreData, containerLoc));
		
		return this;
	}
	
	public ControlDataFactory buildTextSelect(ControlName cntrlName, By containerLoc) {
		controlData = 
				new ControlData(
						cntrlName, 
						new ControlGetterSelect(coreData, containerLoc));
		
		return this;
	}
	
	public ControlData getControlData() {
		return controlData;
	}
	
}
