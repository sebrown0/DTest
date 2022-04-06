/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_results.DynamicTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementTestFactory implements TestContainerLoader {
	private List<DynamicTest> testList;
	private String elName;
	private ControlFinder cntrlFinder;	
	private ControlTest controlTest;
	private boolean itemIsNotLoaded = true;
	private DynamicTestData testData;
	
	private final XmlInfo testInfo;	
		
	public ElementTestFactory(XmlInfo testInfo, DynamicTestData testData) {
		this.testInfo = testInfo;
		this.testData = testData;
	}
	
	public ElementTestFactory createTextCheck(String textExpected) {
		new CreateTextCheckString(testInfo, testData, cntrlFinder, testList, controlTest, textExpected).createTest(elName);
		return this;
	}
	public ElementTestFactory createTextCheck(TestAdderWithData testData) {
		new CreateTextCheckTestData(testInfo, cntrlFinder, testList, controlTest, testData).createTest(elName);
		return this;
	}
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		new CreateToolTipCheck(testInfo, cntrlFinder, testList, controlTest, toolTipText).createTest(elName);
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(String faFa) {
		new CreateFaFaCheck(testInfo, cntrlFinder, testList, controlTest, faFa).createTest(elName);
		return this;
	}
	public ElementTestFactory createTextListCheck(TestAdderWithData testData) {
		new CreateListCheck(testInfo, cntrlFinder, testList, controlTest).createTest(elName);
		return this;
	}

	public ElementTestFactory setTestList(List<DynamicTest> testList) {
		this.testList = testList;
		return this;
	}

	public ElementTestFactory setElementName(String elName) {
		this.elName = elName;
		return this;
	}

	public ElementTestFactory setCntrlFinder(ControlFinder cntrlFinder) {
		this.cntrlFinder = cntrlFinder;
		return this;
	}

	@Override //TestContainerLoader
	public void isItemIsNotLoaded(boolean itemIsNotLoaded) {
		this.itemIsNotLoaded = itemIsNotLoaded;
	}
	@Override //TestContainerLoader
	public boolean itemIsNotLoaded() {
		return itemIsNotLoaded;
	}
	@Override //TestContainerLoader
	public ControlTest getControlTest() {
		return controlTest;
	}
	@Override //TestContainerLoader
	public void setControlTest(ControlTest controlTest) {
		this.controlTest = controlTest;
	}		
}
