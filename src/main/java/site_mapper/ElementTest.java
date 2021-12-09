/**
 * 
 */
package site_mapper;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ElementTest {
	Collection<DynamicTest> getTests();
}
