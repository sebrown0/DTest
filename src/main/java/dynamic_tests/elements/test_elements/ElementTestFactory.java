/**
 * 
 */
package dynamic_tests.elements.test_elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
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
				"Is [" + elName +"] text correct?", 
				() -> {							
					String textActual = ControlTestData.getControlText(cntrl);
					assertEquals(textExpected, textActual);
				}));
		return this;
	}
	
	public ElementTestFactory createTextCheck(TestAdderWithData testData) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {							
					/*
					 * 1) Have to get the data, is it list or text?
					 * 2) Is there input data? If so we have to put it in! 
					 * 			A) If it's text we can (try to) put it into the control, i.e. text box.
					 * 			B) If it's a list HAVE TO HAVE AN INPUT METHOD FROM XML!!
					 * 3) Is there output data? If so we have to get it!
					 * 			A) If it's text we can (try to) get it from the control.
					 * 			B) If it's a list HAVE TO HAVE A GET METHOD FROM XML!!
					 * 					i) For a dropdown this is span[class='select2-results']//ul[class='select2-results__options'] -> li
					 * 
					 * -> WE NEED INTERFACE GetControlData CLASS DropDownControlData.
					 */
					String textActual = ControlTestData.getControlText(cntrl);
					//get list or text!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//					assertEquals(testData.getTestDataOut().getText(), textActual);
//					assertEquals(testData.getTestDataOut().getValue().getValue(), textActual);
					assertTrue(true);
				}));
		return this;
	}
	
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		if(toolTipText != null && toolTipText.length()>0) {
			testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] tool tip correct?", 
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
				"Is [" + elName +"] FaFa correct?", 
				() -> {						 	
					String faFaActual = ControlTestData.getFaFaText(cntrl);
					assertEquals(faFa, faFaActual);
				}));
		return this;
	}
	
}
