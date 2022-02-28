/**
 * 
 */
package dynamic_tests.elements.test_elements;

import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TestAdderWithData extends TestAdder {
	TestDataIn getTestDataIn();
	TestDataOut getTestDataOut();
}
