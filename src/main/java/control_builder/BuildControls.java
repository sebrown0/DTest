/**
 * 
 */
package control_builder;

import java.util.Map;

import control_builder.control_getters.ControlGetter;

/**
 * @author Steve Brown
 *
 */
public interface BuildControls {
	public Map<String, ControlGetter> build();		
}
