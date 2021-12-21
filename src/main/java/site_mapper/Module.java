/**
 * 
 */
package site_mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DynamicContainer;
import org.w3c.dom.NodeList;

import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Module implements NodeAdder {
	private String name;
	private Map<String, ZZZ_Node> nodes = new HashMap<>();
	private HomePage homePage;
	
	public Module(String name, HomePage homePage) {
		this.name = name;
		this.homePage = homePage;
	}

	public Collection<DynamicContainer> runTests() {
		Collection<DynamicContainer> containers = new ArrayList<DynamicContainer>();		
		for (String key : nodes.keySet()) {
			containers.add((DynamicContainer) nodes.get(key).getTests());
		}
		return containers;		
	}
	
	public void mapNodes(NodeList node) {
		Mapper
			.mapTags(node, "Node")
			.forEach( p ->	new ZZZ_Node(this, p, homePage).mapAttributes().mapElements().addToModule() );
	}

	public String getName() {
		return name;
	}

	public ZZZ_Node getNode(String key) {
		return (ZZZ_Node) nodes.get(key);
	}
	
	@Override //NodeAdder
	public void addNode(ZZZ_Node node) {		
		nodes.put(((MapKey) node).getKey(), node);		
	}

}
