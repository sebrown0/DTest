/**
 * 
 */
package content;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 * 	
 * @since 1.0
 *
 */
@XmlRootElement(name="Employee", namespace = "EmpTestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeContent  {
	@XmlElement(name="Required", namespace="Employee")
	private EmployeeRequiredData required;
	
	@XmlElement(name="Optional", namespace="Employee")
	private EmployeeOptionalData optional;
}