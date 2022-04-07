/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.common.DynamicTestInfoFromXml;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;
import dynamic_tests.test_results.DynamicTestData;
import site_mapper.jaxb.pom.DynamicTestInfo;

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
	private DynamicTestData testData;	
	private TestCreator testCreator;	
	
	private final XmlInfo testInfo;
			
	public ElementTestFactory(XmlInfo testInfo, DynamicTestData testData) {
		this.testInfo = testInfo;		
		this.testData = testData;				
	}
	
	public ElementTestFactory createTextCheck(String elName, String textExpected) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckString(elName, testInfo, testData, controlTest, textExpected), testList);		
		return this;
	}
	public ElementTestFactory createTextCheck(String elName, TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateTextCheckTestData(elName, testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}
	public ElementTestFactory createToolTipCheck(String elName, String toolTipText) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateToolTipCheck(elName, testInfo, testData, controlTest, toolTipText), testList);		
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(String elName, String faFa) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		testCreator.createTest(new CreateFaFaCheck(elName, testInfo, testData, controlTest, faFa), testList);		
		return this;
	}
	public ElementTestFactory createTextListCheck(String elName, TestAdderWithData testAdderWithData) {
		testCreator = new TestCreator(cntrlFinder, testList, controlTest);
		//NOT IMPLEMENTED
		testCreator.createTest(new CreateListCheck(elName, testInfo, testData, controlTest, testAdderWithData), testList);		
		return this;
	}

	public ElementTestFactory setTestList(List<DynamicTest> testList) {
		this.testList = testList;
		return this;
	}

//	public ElementTestFactory setElementName(String elName) {
//		testData.setElementName(elName);
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
