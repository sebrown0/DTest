/**
 * 
 */
package providers;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.Employee;
import enums.Gender;
import xml_file.XMLFile;

/**
 * @author Steve Brown
 *
 */
public class EmployeeFromXml implements EmployeeProvider {
	private XMLFile xmlFile = new XMLFile(XMLFileProvider.EMPLOYEE_FILE_PATH);
	private Employee emp;
	private Logger logger = LogManager.getLogger();
	
	@Override
	public Employee getEmployeeAll(String recordNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Employee getEmployeeRequired(String recordNum) {
		NodeList emps = getEmployees();
	  for (int idx = 0; idx < emps.getLength(); idx++) {
	  	Node n = emps.item(idx);
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		Element el = (Element) n;	  		
	  		String id = el.getAttribute("id");
	  		if(id.equals(recordNum)) {
	  			logger.info("Getting employee XML record [" + id + "]");
	  			mapEmployee(n);
	  			break;
	  		}	  		
	  	}
	  }	  
		return emp;
	}
	
	private NodeList getEmployees() {
		return getRoot().getElementsByTagName("Employee");	
	}
	
	private Element getRoot() {
		return xmlFile.getElement("Employees");
	}
	
	private void mapEmployee(Node n) {
		mapRequired((Element )n);
		mapTheRest(n);		
	}
	
	private String getContent(Element e, String tagName) {
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	private void mapRequired(Element required) {		
		NodeList nodes = required.getElementsByTagName("Required");
		Element e = (Element) nodes.item(0);		
		this.emp = Employee.withRequiredFields()
								.empCode(getContent(e, "Code"))
								.firstName(getContent(e, "FirstName"))
								.lastName(getContent(e, "Surname"))
								.idCardNumber(getContent(e, "IdCardNumber"))								
								.addressOne(getContent(e, "AddressOne"))
								.town(getContent(e, "Town"))
								.country(getContent(e, "Country"))
								.gender(getGender(getContent(e, "Gender")))
								.dateOfBirth(getContent(e, "DOB"))
								.dateOfEmployement(getContent(e, "DOE"))
								.taxNumber(getContent(e, "TaxNumber"))
								.niNumber(getContent(e, "NiNumber"))	
								.payGroup(getContent(e, "PayGroup"))
								.annualSalary(getAsCurrency(getContent(e, "AnnualSalary")))								
								.hourlyRate(getAsCurrency(getContent(e, "HourlyRate")))
								.build();	
	}

	private void mapTheRest(Node n) {
		//TODO
	}
	
	private Gender getGender(String g) {
		if(g.equalsIgnoreCase("male")) {
			return Gender.MALE;
		}else if (g.equalsIgnoreCase("female")) {
			return Gender.FEMALE;
		}else {
			return Gender.OTHER;
		}
	}
	
	private BigDecimal getAsCurrency(String val) {
		return BigDecimal.valueOf(Double.valueOf(val));
	}
	
}
