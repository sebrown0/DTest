/**
 * 
 */
package site_mapper;

import java.util.Optional;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getNodeAsSiteMapElement().
 * @version 1.2
 * 	Add getParentPackage().
 * @since 1.0
 */
public interface NodeClass {
	String getParentPackage();
	String getPackage();
	String getClassName();	
	Optional<SiteMapElement> getNodeAsSiteMapElement();
}
