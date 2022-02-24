/**
 * 
 */
package node_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dynamic_tests.mappers.TestNode;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
class TestNode_Tests {
	private static final TestNode ROOT = 
		new TestNode("root");
			
	@Test	
	void test() {
		TestNode tabs = new TestNode("Tabs", ROOT);
		TestNode tabSalDetails = new TestNode("SalDetails", tabs);
		List<String> results = Arrays.asList("root", "Tabs", "SalDetails");
		int idx = 0;
		for(String name : tabSalDetails.getPrntNames()) {
			assertEquals(results.get(idx++), name);
		}		
	}

}
