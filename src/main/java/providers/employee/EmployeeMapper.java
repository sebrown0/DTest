/**
 * 
 */
package providers.employee;

import java.math.BigDecimal;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class EmployeeMapper {

	public abstract void mapFields(Node n);
	
	protected String getContent(Element e, String tagName) {
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	protected BigDecimal getAsCurrency(String val) {
		return BigDecimal.valueOf(Double.valueOf(val));
	}
}
