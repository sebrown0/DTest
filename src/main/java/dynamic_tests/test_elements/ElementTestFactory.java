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
import dynamic_tests.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Make a TestCreator for a specific element.
 * 
 * NOTE: Each element requires a new test.
 * This factory 'belongs' to the test item
 * that is probably a panel that will have
 * multiple elements.
 */
public class ElementTestFactory implements TestContainerLoader {
	private List<DynamicTest> testList;
	private ControlFinder cntrlFinder;	
	private ControlTest controlTest;
	private boolean itemIsNotLoaded = true;
	private DynamicTestSuiteData testData;	
	@SuppressWarnings("unused")
	private TestCreator testCreator;	

	private final XmlInfo testInfo;
	
	public ElementTestFactory(XmlInfo testInfo, DynamicTestSuiteData testData) {
		this.testInfo = testInfo;		
		this.testData = testData;				
	}
	
	public ElementTestFactory createTextCheck(TestElementDetails details, String textExpected) {
		createChecker(new CreateTextCheckString(details, testInfo, testData, controlTest, textExpected));
		return this;
	}
	public ElementTestFactory createTextCheck(TestElementDetails details, TestAdderWithData testAdderWithData) {
		createChecker(new CreateTextCheckTestData(details, testInfo, testData, controlTest, testAdderWithData));
		return this;
	}
	public ElementTestFactory createToolTipCheck(TestElementDetails details, String toolTipText) {
		createChecker(new CreateToolTipCheck(details, testInfo, testData, controlTest, toolTipText));
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(TestElementDetails details, String faFa) {
		createChecker(new CreateFaFaCheck(details, testInfo, testData, controlTest, faFa));
		return this;
	}
	public ElementTestFactory createTextListCheck(TestElementDetails details, TestAdderWithData testAdderWithData) {
		//NOT IMPLEMENTED
		createChecker(new CreateListCheck(details, testInfo, testData, controlTest, testAdderWithData));
		return this;
	}

	private void createChecker(TestElementCreator elementTestCreator) {
		testCreator = 
			new TestCreator(cntrlFinder, testList, controlTest)
				.createTest(elementTestCreator, testList);
	}
	
	public ElementTestFactory setTestList(List<DynamicTest> testList) {
		this.testList = testList;
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
