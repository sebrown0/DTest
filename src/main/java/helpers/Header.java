/**
 * 
 */
package helpers;

import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public interface Header {
	Optional<String> getTitle();
	void closeForm();
}
