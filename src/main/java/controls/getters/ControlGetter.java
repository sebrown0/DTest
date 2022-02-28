/**
 * 
 */
package controls.getters;

import java.util.Optional;

import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ControlGetter <R extends Control> {
	Optional<R> getControl();
}
