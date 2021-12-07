/**
 * 
 */
package site_map_tests;

import org.junit.jupiter.api.Test;

import controls.ControlName;
import enums.control_names.GroupControlNames;
import site_mapper.SiteMapper;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
class SiteMapTests {

	@Test
	void test() {
		SiteMapper mapper = new SiteMapper();
		mapper.mapModules();
	}
	
	@Test
	void ddddddddg() {
		ControlName n = GroupControlNames.SAVE;
		System.out.println("->" + n.getName()); // TODO - remove or log 	
	}

}
