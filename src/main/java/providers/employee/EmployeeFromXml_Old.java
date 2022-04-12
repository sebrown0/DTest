/**
 * 
 */
package providers.employee;

import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.Employee;
import providers.XMLFileProvider;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Implement getEmployeeAll(...). 
 * @since 1.0
 *
 */
public class EmployeeFromXml_Old implements EmployeeProvider, RandomEmployeeProvider {

	private XMLFile xmlFile = new XMLFile(XMLFileProvider.EMPLOYEE_TESTS_FILE_PATH);
	private Employee emp;
	private Logger logger = LogManager.getLogger();
	
	@Override // RandomEmployeeProvider
	public Employee getAnyEmpWithRandomCode() {
		Employee fromXml = getEmployeeWithRequiredFields("1");
		fromXml.setEmpCode(getRandomCode());
		return fromXml;
	}
	
	private String getRandomCode() {
		String code = "";
		
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			code += (char)(r.nextInt(26) + 'a'); 
		}
		return code;
	}
	
	@Override // EmployeeProvider
	public Employee getEmployeeWithAllFields(String recordId) {
		return mapFields(
				recordId, 
				Arrays.asList(
						new EmployeeRequiredFieldsMapper(getEmp()), 
						new EmployeeOptionalFieldsMapper(getEmp())));
	}
	
	@Override // EmployeeProvider
	public Employee getEmployeeWithRequiredFields(String recordId) {			  
		return mapFields(
				recordId, 
				Arrays.asList(
						new EmployeeRequiredFieldsMapper(getEmp())));
	}
	
	private Employee mapFields(String recordId, java.util.List<EmployeeMapper> mappers) {
		NodeList emps = getEmployees();
	  for (int idx = 0; idx < emps.getLength(); idx++) {
	  	Node n = emps.item(idx);
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		Element el = (Element) n;	  		
	  		String id = el.getAttribute("id");
	  		if(id.equals(recordId)) {
	  			logger.info("Getting employee XML record [" + id + "]");
	  			mappers.forEach(m -> m.mapFields(n));
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
		
	private Employee getEmp() {
		if(emp == null) {
			emp = new Employee();
		}
		return emp;				
	}
		
}
