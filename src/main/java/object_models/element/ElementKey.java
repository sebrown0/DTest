/**
 * 
 */
package object_models.element;

import java.util.List;

import dto.ButtonData;

/**
 * @author Steve Brown
 *
 */
public interface ElementKey {
	/*
	 * ONLY USING THIS FOR ElementButton
	 * IF WE NEED IT FOR ANYTHING ELSE WILL 
	 * HAVE TO HAVE GENERIC LIST
	 */
	String getKey(List<ButtonData> elements);
}
