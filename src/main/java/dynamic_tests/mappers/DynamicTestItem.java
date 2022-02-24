/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.elements.TestElement;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.containers.TreeVisitor;
import site_mapper.jaxb.containers.TreeWalker;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;

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
//	private List<Node> nodes = new ArrayList<>();
//	private List<Element> elements;
	private TestNode lastTestNode;
	private List<TestNode> testNodes = new ArrayList<>();
	private MenuItem item;
	private CoreData coreData;
	private List<DynamicContainer> menuItemTests;
	private HomePage hp;
	private DynamicTestFactory testFactory;
	
	/** 
	 * @param includedElements: all elements that are included in the test.
	 * @param item: menu item from xml.
	 * @param menuItemTests: List of tests for the item. 
	 * @param hp: Home page.
	 */
	public DynamicTestItem(
			IncludedElements includedElements, MenuItem item,	
			List<DynamicContainer> menuItemContainers, HomePage hp) {
		
		this.includedElements = includedElements;
		this.menuItemTests = menuItemContainers;
		this.hp = hp;
		this.coreData = hp;
		this.item = item;
		this.testFactory = new DynamicTestFactory(hp, hp, item);
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
		
			testNodes.forEach(n -> {
//				System.out.println(n.toString()); // TODO - remove or log
					
			});	
	
	}

	@Override //TreeVisitor
	public void addNode(Node node) {
		TestNode testNode = 
			new TestNode(
					node.getName(), lastTestNode, node.getElements());
		testNodes.add(testNode);
		
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
			testNodes.forEach(tn -> {
				List<Element> els = tn.getElements();
				if(els != null) {
					els.forEach(el -> {
						if(includedElements.isIncluded(el.getElementType())) {
							addTestToItemContainer(
									//PASS FINDER!!!!!!!!!!
									testFactory.getTest(el)
//									DynamicTestFactory.getTest(tn, hp, coreData, item)
							);
						}	
					});							
				}											
			});	
		}		
	}
//	public void addTests() {
//		if(elements != null) {			
//			elements.forEach(e -> {
//				if(includedElements.isIncluded(e.getElementType())) {
//					addTestToItemContainer(
//							DynamicTestFactory.getTest(e, hp, coreData, item)
//					);
//				}								
//			});	
//		}		
//	}
	
//	private String getNav(TestNode tn) {
//		String nav = "";
//		if(tn != null) {
//			nav = tn.getName();
//			TestNode prev = tn.getParent();
//			while(prev != null) {
//				nav += "." + prev.getName();
//				prev = prev.getParent();
//			}	
//		}
//		return nav;
//	}

			
	private void addTestToItemContainer(Optional<TestElement> test) {
		test.ifPresent(t -> {	
			menuItemTests.add(DynamicContainer.dynamicContainer(getKey(t), t.createTests())); 
		});
	}
	
//public void addTests() {
//if(testNodes != null) {			
//	testNodes.forEach(tn -> {
//		System.out.println("Node: " + tn.getName()); // TODO - remove or log
//		
//		tn.getElements().forEach(el -> {
//			System.out.println("Element: " + el.getElementName() + "\tNav: " + getNav(tn)); // TODO - remove or log 		
////			System.out.println(e.getName() + "-" + el.getElementName()); // TODO - remove or log
//		});								
//	});	
//}		
//}
	
	private String getKey(TestElement e) {
		return e.getType() + "." + e.getName();
	}
	
}
