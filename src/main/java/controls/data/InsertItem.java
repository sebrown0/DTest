/**
 * 
 */
package controls.data;

import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface InsertItem {
	/*
	 * Use a TestDataInserter, 
	 * (found from the item's insertWith) 
	 * to insert the value.
	 */
	void insert(TestDataItem item);
	/*
	 * No TestDataInserter is specified so try using send keys
	 * on the control. 
	 */
	void insert(String value);
}
