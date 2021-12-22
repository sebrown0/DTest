/**
 * 
 */
package site_mapper.elements;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TestElement {
	String getName();
	String getType();
	TestElement createTests(ElementLoader loader);
	List<DynamicTest> getTests();
}
