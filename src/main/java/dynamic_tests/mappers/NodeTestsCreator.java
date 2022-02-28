/**
 * 
 */
package dynamic_tests.mappers;

import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.test_elements.DynamicTestFactory;
import dynamic_tests.test_elements.ElementTest;
import dynamic_tests.test_elements.TestElementDetails;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 * 
 * Add the specific tests for each 
 * test node in the item, 
 * i.e. all the tests for a button in an input group.
 */
public class NodeTestsCreator {

	private List<TestNode> testNodes;
	private IncludedElements includedElements;
	private MenuItem item;;
	private HomePage hp;
	private List<DynamicContainer> menuItemTests;
	
	private DynamicTestFactory testFactory = new DynamicTestFactory();
	
	public NodeTestsCreator(
		List<TestNode> testNodes, IncludedElements includedElements, 
		MenuItem item, HomePage hp,	List<DynamicContainer> menuItemTests) {
		
		this.testNodes = testNodes;
		this.includedElements = includedElements;
		this.item = item;
		this.hp = hp;
		this.menuItemTests = menuItemTests;
	}

	public NodeTestsCreator addElementTestsForEachTestNode() {
		testNodes.forEach(tn -> addTestsForElements(tn));	
		return this;
	}
		
	private void addTestsForElements(TestNode tn) {
		List<Element> els = tn.getElements();
		if(els != null) {
			els.forEach(el -> addTestForElement(tn, el));							
		}			
	}
	
	private void addTestForElement(TestNode tn, Element el) {
		if(includedElements.isIncluded(el.getElementType())) {
			ElementTest elTest = new ElementTest(tn, hp, item, el);
			testFactory.addElementSpecificTestsTo(elTest, el);
			addTestToItemContainer(elTest);
		}
	}
	
	private void addTestToItemContainer(ElementTest t) {		
		menuItemTests.add(
				DynamicContainer.dynamicContainer(getKey(t), t.getTestList()));		
	}
		
	private String getKey(TestElementDetails e) {
		return e.getType() + "." + e.getName();
	}
}
