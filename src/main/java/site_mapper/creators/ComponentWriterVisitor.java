/**
 * 
 */
package site_mapper.creators;

import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriterVisitor extends ComponentWriter {
	void writeComponents(ClassWriterActions fileOut, ElementClass elementClass);
}
