/**
 * 
 */
package dynamic_tests.test_adders;

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
