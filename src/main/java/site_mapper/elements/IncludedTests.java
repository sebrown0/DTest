/**
 * 
 */
package site_mapper.elements;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Check the list of included tests to see if
 * a specific element is to be included in the
 * menu item's tests.
 */
public class IncludedTests implements IncludedElements {
	private List<String> elements;
	
	public IncludedTests(List<String> elements) {
		this.elements = elements;
	}

	@Override
	public boolean isIncluded(String value) {
		return elements.contains(value);
	}

}
