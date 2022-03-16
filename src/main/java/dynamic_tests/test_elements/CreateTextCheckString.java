/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckString extends TestCreator {
	private String textExpected;
	
	public CreateTextCheckString(
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String textExpected) {
		
		super(cntrlFinder, testList, controlTest);

		this.textExpected = textExpected;	
	}
	
	@Override
	public void createTest(String elName) {
		testList.add(
		DynamicTest.dynamicTest(
			"Is [" + elName +"] text correct?", 
			() -> {
				getControlAndParent();
				new 
					AssertTextEquals(controlTest, cntrl)
						.assertTextEquals(textExpected);
				
//				WebDriver d = super.cntrlFinder.ZZZ_getDriver();
//				WebElement el = d.findElement(By.cssSelector("input[id='FORM_id']"));
//				System.out.println("->" + el.getText()); // TODO - remove or log 	
			}));		
	}
		
}
