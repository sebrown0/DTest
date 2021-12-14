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
 * @since 1.0
 */
public interface NodeClass {
	String getPath();
	String getClassName();	
	Optional<SiteMapElement> getNodeAsSiteMapElement();
}
