/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.test_elements.ElementTestFactory;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.containers.TreeVisitor;
import site_mapper.jaxb.containers.TreeWalker;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add all the included tests to the item's 
 * list of tests.
 */
public class DynamicTestItem implements TreeVisitor {
	private IncludedElements includedElements;
	private TestNode lastTestNode;
	private List<TestNode> testNodes = new ArrayList<>();
	private MenuItem item;
	private List<DynamicContainer> menuItemTests;
	private HomePage hp;
	
	public DynamicTestItem(
			IncludedElements includedElements, MenuItem item,	
			List<DynamicContainer> menuItemContainers, HomePage hp) {
		
		this.includedElements = includedElements;
		this.menuItemTests = menuItemContainers;
		this.item = item;
		this.hp = hp;
		 	
		getElements();
	}
	
	private void getElements() {
		TreeWalker treeWalker = 
				new TreeWalker(
						this,
						new Node(item.getHeader()),
						new Node(item.getBody()),
						new Node(item.getFooter()));
						
		treeWalker.traverseTree();	
	}

	@Override //TreeVisitor
	public void addNode(Node node) {
		TestNode testNode = 
			new TestNode(
					node.getName(), lastTestNode, node.getElements());
		testNodes.add(testNode);
		setLastNode(testNode, node);				
	}
	
	private void setLastNode(TestNode testNode, Node node) {
		if(isParent(node)) {
			lastTestNode = testNode;	
		}else {
			lastTestNode = null;
		}
	}
	private boolean isParent(Node n) {
		List<Container> children = n.getContainers();
		return (children != null && children.size() > 0 ) ? true : false;
	}

	public void addTests() {		
		if(testNodes != null) {				
			NodeTestsCreator nodeTests = 
				new NodeTestsCreator(
					testNodes, includedElements, item, hp, menuItemTests);
			nodeTests.addElementTestsForEachTestNode(new ElementTestFactory(item));
		}
	}
		
}
