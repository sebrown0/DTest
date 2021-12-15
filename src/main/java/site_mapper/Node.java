/**
 * 
 */
package site_mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import object_models.pages.homepage.HomePage;
import site_mapper.class_finder.ClassFinder;
import site_mapper.elements.ElementFactory;
import site_mapper.elements.NodeElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 *  Create and run tests on demand.
 * @since 1.0
 */
public class Node implements MapKey, ElementAdder, NodeTest, NodeClass {
	private NodeAdder nodeAdder;
	private Element node;
	private HomePage homePage;
	
	private String type;
	private String title;
	private String objectName;
	private String navPath;
	
	private Map<String, NodeElement> nodeElements = new HashMap<>();
		
	public Node(NodeAdder nodeAdder, Element node, HomePage homePage) {
		this.nodeAdder = nodeAdder;
		this.node = node;
		this.homePage = homePage;
	}

	public Node mapElements() {		
		Mapper
			.mapTags(node, "Element")
				.forEach( n ->	ElementFactory.getNodeElement(n, this) );
		return this;
	}

	public Node mapAttributes() {
		type = node.getAttribute("type");
		objectName = node.getAttribute("obj");
		title = node.getAttribute("title");
		navPath = node.getAttribute("nav");
		
//		System.out.println(" " + this.toString()); // TODO - remove or log
 	
		return this;
	}
	
	public String getTitle() {
		return title;
	}

	public Class<?> getClazz(){
		return ClassFinder.getClazz(this);
	}
	
	public void addToModule() {
		nodeAdder.addNode(this);
	}
		
	@Override // MapKey
	public String getKey() {
		return navPath;
	}
	
	@Override // ElementAdder
	public void addElement(NodeElement nodeElement) {
		nodeElements.put(((MapKey)nodeElement).getKey(), nodeElement);
	}

	@Override
	public DynamicNode getTests() {		
		return DynamicContainer.dynamicContainer(navPath, getNodeTests());
	}

	private Collection<DynamicTest> getNodeTests(){
		Collection<DynamicTest> tests = new ArrayList<>();		
		nodeElements.entrySet().forEach(s -> { 
			NodeElement n = s.getValue(); 			
			tests.addAll(n.createTests().getTests());			
		});
		return tests;
	}

	@Override //NodeClass
	public String getPath() {
		return navPath;
	}
	@Override //NodeClass
	public String getClassName() {
		return objectName;
	}
	@Override //NodeClass
	public Optional<SiteMapElement> getNodeAsSiteMapElement() {		
		Class<?> clazz = getClazz();		
		return 
			Optional.ofNullable(
				SiteMapElementLoader.getAndLoadSiteMapElement(navPath, homePage, clazz));
	}
	
	@Override
	public String toString() {
		return "Node [type=" + type + ", title=" + title + ", objectName=" + objectName + ", navPath=" + navPath + "]";
	}

}
