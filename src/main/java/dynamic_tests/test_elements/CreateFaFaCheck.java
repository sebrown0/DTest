/**
 * 
 */
package dynamic_tests.test_elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.data.ControlTestData;
import controls.interfaces.ControlTest;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateFaFaCheck extends TestCreator {
	private String faFa;
	
	public CreateFaFaCheck(
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String faFa) {
		
		super(cntrlFinder, testList, controlTest);

		this.faFa = faFa;
	}
	
	@Override
	public void createTest(String elName) {		
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] FaFa correct?", 
				() -> {						 	
					if(elName.equalsIgnoreCase("EmployeeList")) {
						System.out.println("->"); // TODO - remove or log 	
					}
					
					
//				WebDriver d = super.cntrlFinder.ZZZ_getDriver();
//				WebElement el = d.findElement(By.cssSelector("input[id='FORM_id']"));
//				System.out.println("->" + el.getText()); // TODO - remove or log 	
					
					getControlAndParent();
					String faFaActual = ControlTestData.getFaFaText(cntrl);
					assertEquals(faFa, faFaActual);
				}));
	}
		
}
