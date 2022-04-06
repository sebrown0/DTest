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
	
	private final XmlInfo testInfo;
		
	public ElementTestFactory(XmlInfo testInfo) {
		this.testInfo = testInfo;
	}
	
	public ElementTestFactory createTextCheck(String textExpected) {
//		DynamicTestReportStrategy strat = new DynamicTestReportStrategyJunit();
//		strat = new DynamicTestReportStrategyConsole();
		new CreateTextCheckString(testInfo, cntrlFinder, testList, controlTest, textExpected).createTest(elName);
		return this;
	}
	public ElementTestFactory createTextCheck(TestAdderWithData testData) {
		new CreateTextCheckTestData(cntrlFinder, testList, controlTest, testData).createTest(elName);
		return this;
	}
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		new CreateToolTipCheck(cntrlFinder, testList, controlTest, toolTipText).createTest(elName);
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(String faFa) {
		new CreateFaFaCheck(cntrlFinder, testList, controlTest, faFa).createTest(elName);
		return this;
	}
	public ElementTestFactory createTextListCheck(TestAdderWithData testData) {
		new CreateListCheck(cntrlFinder, testList, controlTest).createTest(elName);
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
