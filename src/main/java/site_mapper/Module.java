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

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Module implements NodeAdder {
	private String name;
	private Map<String, Node> nodes = new HashMap<>();
	
	public Module(String name) {
		this.name = name;
//		System.out.println(name); // TODO - remove or log 	
	}

	public Collection<DynamicContainer> runTests() {
		Collection<DynamicContainer> containers = new ArrayList<DynamicContainer>();		
		for (String node : nodes.keySet()) {
			containers.add((DynamicContainer) nodes.get(node).getTests());
		}
		return containers;		
	}
	
	public void mapNodes(NodeList node) {
		Mapper
			.mapTags(node, "Node")
			.forEach( p ->	new Node(this, p).mapAttributes().mapElements().addToModule() );
	}

	public String getName() {
		return name;
	}

	public Node getNode(String key) {
		return (Node) nodes.get(key);
	}
	
	@Override //NodeAdder
	public void addNode(Node node) {		
		nodes.put(((MapKey) node).getKey(), node);		
	}

}
