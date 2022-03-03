/**
 * 
 */
package dynamic_tests.test_elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.data.ControlTestData;
import controls.interfaces.ControlTest;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateToolTipCheck extends TestCreator {
	private String toolTipText;
	
	public CreateToolTipCheck(
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String toolTipText) {
		super(cntrlFinder, testList, controlTest);

		this.toolTipText = toolTipText;
	}
	
	@Override
	public void createTest(String elName) {		
		if(toolTipText != null && toolTipText.length()>0) {
			testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] tool tip correct?", 
					() -> {							
						getControlAndParent();
						String textActual = ControlTestData.getControlToolTip(cntrl);
						assertEquals(toolTipText, textActual);
					}));			
		}
	}
		
}
