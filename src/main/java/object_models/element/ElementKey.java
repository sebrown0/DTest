/**
 * 
 */
package object_models.element;

import java.util.List;

/**
 * @author Steve Brown
 *
 */
public interface ElementKey {	
	<T extends DataKey> String getKey(List<T> elements);
}
