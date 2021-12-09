/**
 * 
 */
package site_mapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import site_mapper.elements.ElementFactory;
import site_mapper.elements.NodeElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public class Node implements MapKey, ElementAdder, NodeTest {
	private NodeAdder nodeAdder;
	private Element node;
	
	private String type;
	private String title;
	private String objectName;
	private String navPath;
	
	private Map<String, NodeElement> elements = new HashMap<>();
		
	public Node(NodeAdder nodeAdder, Element node) {
		this.nodeAdder = nodeAdder;
		this.node = node;
	}

	public Node mapElements() {		
		Mapper
			.mapTags(node, "Element")
				.forEach( b ->	ElementFactory.getNodeElement(b, this) );
		return this;
	}

	public Node mapAttributes() {
		type = node.getAttribute("type");
		objectName = node.getAttribute("obj");
		title = node.getAttribute("title");
		navPath = node.getAttribute("nav");
		
		System.out.println(" " + this.toString()); // TODO - remove or log
 	
		return this;
	}
	
	public String getTitle() {
		return title;
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
		elements.put(((MapKey)nodeElement).getKey(), nodeElement);
	}

	@Override
	public String toString() {
		return "Node [type=" + type + ", title=" + title + ", objectName=" + objectName + ", navPath=" + navPath + "]";
	}

	@Override
	public DynamicNode getTests() {		
		return DynamicContainer.dynamicContainer(navPath, getNodeTests());
	}

	private Collection<DynamicTest> getNodeTests(){		
		return elements.get("save").getTests();		
	}
}
