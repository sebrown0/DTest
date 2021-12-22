/**
 * 
 */
package site_mapper.elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getName & getType.
 * @since 1.0
 */
public interface TestElement {
	String getName();
	String getType();
	TestElement createTests(ElementLoader loader);
	List<DynamicTest> getTests();
}
