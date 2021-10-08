/**
 * 
 */
package object_models.helpers;

/**
 * @author Steve Brown
 *
 * Reload a page/iFrame.
 * The page is passed to a 'child' when the child
 * closes the reload method of the 'parent' is called.
 * 
 */
public interface Reload {
	void reloadDefault();
}
