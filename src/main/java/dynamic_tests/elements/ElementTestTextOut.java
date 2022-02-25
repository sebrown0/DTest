/**
 * 
 */
package dynamic_tests.elements;

import site_mapper.elements.ElementCreation;
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
public class ElementTestTextOut implements TestAdder {
	private ElementTestData dataIn;
//	private ElementTestData dataOut;

	public ElementTestTextOut(ElementCreation el) {
			this.dataIn = el.getTestDataIn();
//			this.dataOut = el.getTestDataOut();
		}

	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
		checkDataIn();
		testFactory.createTextCheck("text");
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
