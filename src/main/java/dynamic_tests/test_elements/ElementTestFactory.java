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
	private TestCreator testCreator;	
//	private TestElementDetails testElementDetails;
	
	private final XmlInfo testInfo;
	
	
	public ElementTestFactory(XmlInfo testInfo, DynamicTestSuiteData testData) {
		this.testInfo = testInfo;		
		this.testData = testData;				
	}
	
	public ElementTestFactory createTextCheck(TestElementDetails details, String textExpected) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckString(details, testInfo, testData, controlTest, textExpected), testList);		
		return this;
	}
	public ElementTestFactory createTextCheck(TestElementDetails details, TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckTestData(details, testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}
	public ElementTestFactory createToolTipCheck(TestElementDetails details, String toolTipText) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateToolTipCheck(details, testInfo, testData, controlTest, toolTipText), testList);		
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(TestElementDetails details, String faFa) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateFaFaCheck(details, testInfo, testData, controlTest, faFa), testList);		
		return this;
	}
	public ElementTestFactory createTextListCheck(TestElementDetails details, TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		//NOT IMPLEMENTED
		testCreator.createTest(new CreateListCheck(details, testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}

	public ElementTestFactory setTestList(List<DynamicTest> testList) {
		this.testList = testList;
		return this;
	}

//	public ElementTestFactory setTestElementDetails(TestElementDetails testElementDetails) {
//		this.testElementDetails = testElementDetails;
//		return this;
//	}

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
