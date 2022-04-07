/**
 * 
 */
package dynamic_tests.common;

import java.util.List;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.test_strategy.DynamicTestReportStrategy;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * The information elements from the XML file.
 */
public interface XmlInfo {
	IncludedElements getIncludedElements();	
	List<String> getReportOnTests();
	DynamicTestReportStrategy getTestReportStrategy();
}
