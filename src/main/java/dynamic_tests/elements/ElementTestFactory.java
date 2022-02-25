/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class ElementTestFactory {
	private List<DynamicTest> testList;
	private String elName;
	private Optional<Control> cntrl;
	
	public ElementTestFactory(
		List<DynamicTest> testList, String elName, Optional<Control> cntrl) {
		
		this.testList = testList;
		this.elName = elName;		
		this.cntrl = cntrl;
	}
	
	public ElementTestFactory createTextCheck(String textExpected) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] button [text] correct?", 
				() -> {							
					String textActual = ControlTestData.getControlText(cntrl);
					assertEquals(textExpected, textActual);
				}));
		return this;
	}
	
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		if(toolTipText != null && toolTipText.length()>0) {
			testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] button [tool tip] correct?", 
					() -> {							
						String textActual = ControlTestData.getControlToolTip(cntrl);
						assertEquals(toolTipText, textActual);
					}));			
		}	
		return this;
	}
	
	public ElementTestFactory createButtonFaFaCheck(String faFa) {				
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] button [FaFa] correct?", 
				() -> {						 	
					String faFaActual = ControlTestData.getFaFaText(cntrl);
					assertEquals(faFa, faFaActual);
				}));
		return this;
	}
	
}
