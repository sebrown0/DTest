/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.elements.ControlFinder;
import dynamic_tests.test_adders.TestAdderWithData;
import site_mapper.jaxb.menu_items.MenuItem;

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
	private ControlTest controlTest;
	private MenuItem item;	
	private boolean itemIsNotLoaded = true;
//	private final Logger LOGGER = LogManager.getLogger(ElementTestFactory.class);

	public ElementTestFactory(MenuItem item) {
		this.item = item;
		System.out.println("Creating factory for: " + item.getName()); // TODO - remove or log 
	}
	
//	public void loadItem() {
//		if(itemIsNotLoaded) {
//			itemIsNotLoaded = false;
//			cntrlFinder.loadConatiner();
//			System.out.println("Load item: " + item.getName()); // TODO - remove or log 	
//		}
//	}
			
	public ElementTestFactory createTextCheck(String textExpected) {
		new CreateTextCheckString(cntrlFinder, testList, controlTest, textExpected, this).createTest(elName);
		return this;
	}
	public ElementTestFactory createTextCheck(TestAdderWithData testData) {
		new CreateTextCheckTestData(cntrlFinder, testList, controlTest, testData).createTest(elName);;
		return this;
	}
	public ElementTestFactory createToolTipCheck(String toolTipText) {
		new CreateToolTipCheck(cntrlFinder, testList, controlTest, toolTipText);
		return this;
	}
	public ElementTestFactory createButtonFaFaCheck(String faFa) {
		new CreateFaFaCheck(cntrlFinder, testList, controlTest, faFa);
		return this;
	}
	public ElementTestFactory createTextListCheck(TestAdderWithData testData) {
		new CreateListCheck(cntrlFinder, testList, controlTest);
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

	
}
