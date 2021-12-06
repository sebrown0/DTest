/**
 * 
 */
package site_mapper;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Map the tags from an element or node list.
 */
public class Mapper {

	public static List<Element> mapTags(Element element, String tagName) {		
		NodeList nodes = element.getElementsByTagName(tagName);		
		return mapTags(nodes, tagName);
	}
	
	public static List<Element> mapTags(NodeList nodes, String tagName) {
		List<Element> elements = new ArrayList<>();		
		
		for (int idx = 0; idx < nodes.getLength(); idx++) {
	  	Node n = nodes.item(idx);
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		elements.add((Element)n);
	  	}
		}
		return elements;
	}
	
}
