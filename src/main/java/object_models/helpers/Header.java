/**
 * 
 */
package object_models.helpers;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public interface Header {
	Optional<String> getTitle();
	void closeForm();
}
