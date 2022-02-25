/**
 * 
 */
package dynamic_tests.elements;

import site_mapper.jaxb.pom.ElementTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TestAdderWithData extends TestAdder {
	ElementTestData getTestDataIn();
	ElementTestData getTestDataOut();
}
