/**
 * 
 */
package site_mapper.elements;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getNodeAsSiteMapElement().
 * @version 1.2
 * 	Add getParentPackage().
 * @version 1.3
 * 	Add getModuleName().
 * @since 1.0
 */
public interface ElementClass {
	String getParentPackage();
	String getPackage();
	String getClassName();	
	String getModuleName();
	String getType();
}
