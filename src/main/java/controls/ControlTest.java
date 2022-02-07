/**
 * 
 */
package controls;

import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ControlTest {
	public ControlTest loadParent(ElementClass item, HomePage hp);
	public String getFaFaText(String controlName);
	public String getControlText(String controlName);
}
