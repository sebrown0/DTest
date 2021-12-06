/**
 * 
 */
package site_map_tests;

import org.junit.jupiter.api.Test;

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
		mapper.mapPayroll();
	}

}
