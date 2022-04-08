/**
 * 
 */
package controls.data;

import java.util.List;

import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface InsertItems {
	void insert(List<TestDataItem> items);
}
