/**
 * 
 */
package site_mapper.elements;

import org.w3c.dom.Element;

import site_mapper.ElementAdder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Stop tests being created when the element is created.
 * @since 1.0
 */
public class ElementFactory {
	
	public static ZZZ_NodeElement getNodeElement(Element element, ElementAdder elementAdder) {
		ZZZ_NodeElement nodeElement = null;		
		String type = element.getAttribute("type");
		
		switch (type) {
		case "button":
		case "Button":
			System.out.println("************** DEPRICIATED **************"); // TODO - remove or log 	
//			nodeElement = new ElementButton(element, elementAdder);			
			break;

		default:
			break;
		}
		
//		if(nodeElement != null) { nodeElement.mapAttributes().addToNode(); }
		return nodeElement;
	}
}
