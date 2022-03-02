/**
 * 
 */
package dynamic_tests.test_elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTest;
import controls.ControlTestData;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;

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
	private final Logger LOGGER = LogManager.getLogger(ElementTestFactory.class);
	
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
					new 
						AssertTextEquals(controlTest, cntrl)
							.assertTextEquals(textExpected);
				}));
		return this;
	}
	
	public ElementTestFactory createTextCheck(TestAdderWithData testData) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {			
					TestDataInserter.insertAnyTestData(testData, controlTest);
					new 
						AssertTextEquals(controlTest, cntrl)
							.assertTextEquals(testData); 	
				}));
		return this;
	}
		
	public ElementTestFactory createTextListCheck(TestAdderWithData testData) {
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {			
					fail("ElementTestFactory.createTextListCheck not implemented.");
					LOGGER.error("ElementTestFactory.createTextListCheck not implemented.");
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
