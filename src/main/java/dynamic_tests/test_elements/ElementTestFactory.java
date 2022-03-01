/**
 * 
 */
package dynamic_tests.test_elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTest;
import controls.ControlTestData;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_data.TestDataInput;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementTestFactory {
	private List<DynamicTest> testList;
	private String elName;
	private ControlFinder cntrlFinder;
	
	private Optional<Control> cntrl;	
	private ControlTest controlTest;
	
	public ElementTestFactory(
			List<DynamicTest> testList, 
			String elName, 
			ControlFinder cntrlFinder) {
			
			this.testList = testList;
			this.elName = elName;
			this.cntrlFinder = cntrlFinder;			

			getControlAndParent();
		}
	
	private void getControlAndParent() {
		this.cntrl = cntrlFinder.loadControl().getControl();
		this.controlTest = cntrlFinder.getControlsClass();
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
					TestDataInput dataInserter = 
							new TestDataInput(testData.getTestDataIn(), controlTest);
					dataInserter.insertData();
							
							
					String textActual = ControlTestData.getControlText(cntrl);
					TestDataOut dataOut = testData.getTestDataOut();
					if(dataOut != null) {
						//this will have to be checked for Text or List!!!!!!!!!!!
						assertEquals(dataOut.getTestData().getValue(), textActual);	
					}else {
						fail("No input data to compare with actual data.");
					}					 	
				}));
		return this;
	}
	
	public ElementTestFactory createTextCheck_XXX(TestAdderWithData testData) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {			
					TestDataInput dataInserter = 
							new TestDataInput(testData.getTestDataIn(), controlTest);
					dataInserter.insertData();
							
					/*
					 * this will have to be different for DisplayedText & DisplayedTextList
					 */
					String textActual = ControlTestData.getControlText(cntrl);
					TestDataOut dataOut = testData.getTestDataOut();
					if(dataOut != null) {
						//this will have to be checked for Text or List!!!!!!!!!!!
						assertEquals(dataOut.getTestData().getValue(), textActual);	
					}else {
						fail("No input data to compare with actual data.");
					}					 	
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
