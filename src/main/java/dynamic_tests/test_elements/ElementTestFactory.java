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
//	private String elName;
	private ControlFinder cntrlFinder;	
	private ControlTest controlTest;
	private boolean itemIsNotLoaded = true;
	private DynamicTestData testData;
	
	private TestCreator testCreator;
	
	private final XmlInfo testInfo;	
		
	public ElementTestFactory(XmlInfo testInfo, DynamicTestData testData) {
		this.testInfo = testInfo;
		this.testData = testData;		
	}
	
	public ElementTestFactory createTextCheck(String textExpected) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckString(testInfo, testData, controlTest, textExpected), testList);		
		return this;
	}
	public ElementTestFactory createTextCheck(TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckTestData(testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateToolTipCheck(testInfo, testData, controlTest, toolTipText), testList);		
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(String faFa) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateFaFaCheck(testInfo, testData, controlTest, faFa), testList);		
		return this;
	}
	public ElementTestFactory createTextListCheck(TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		//NOT IMPLEMENTED
		testCreator.createTest(new CreateListCheck(testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}

	public ElementTestFactory setTestList(List<DynamicTest> testList) {
		this.testList = testList;
		return this;
	}

	public ElementTestFactory setElementName(String elName) {
		testData.setElementName(elName);
//		this.elName = elName;
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
