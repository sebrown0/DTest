/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestData;
import dynamic_tests.mappers.TestNode;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.ElementTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Move loading and getting of control into super implementation.
 * @version 1.2
 * 	Convert to JAXB class.
 * @version 1.3
 * 	Revert to standard class.
 * @version 1.4
 * 	Pass the container that has the control test.
 * @since 1.0
 */
public class ElementTestTextOut extends ElementTest {
	private ElementTestData dataIn;
	private ElementTestData dataOut;
	
	//TODO Where do we get expected from???
	public ElementTestTextOut(
			TestNode testNode, HomePage hp, MenuItem item, 
			 ElementCreation el) {
		super(testNode, hp, item, "TextOut", el.getElementName());

		this.dataIn = el.getTestDataIn();
		this.dataOut = el.getTestDataOut();
	}
;	

	@Override //TestElement 
	public List<DynamicTest> createTests() {
		createTextCheck();
		return super.getTests();		
	}

	/*
	 * this will have to be a check based on the data,
	 * i.e. text or list.
	 */
	private void createTextCheck() {
		super.getTests().add(
				DynamicTest.dynamicTest(
					"Is [" + super.getName() +"] [text] correct?", 
					() -> {							
						checkDataIn();
						String textActual = ControlTestData.getControlText(super.getControl());
						assertEquals("textExpected", textActual);
					}
				)
			);
	}

	private void checkDataIn() {
		if(dataIn != null) {
			System.out.println("NEED TO INPUT THIS DATA!!");
			if(dataIn.getText() != null) {
				System.out.println(dataIn.getText()); // TODO - remove or log 	
			}else if (dataIn.getList() != null) {
				dataIn.getList().forEach(item -> System.out.println(item));
			}else {
				System.out.println("NO DATA FOUND!"); // TODO - remove or log 	
			}
		}
	}

}
