/**
 * 
 */
package providers.employee;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.Employee;
import dto.EmployeeOptional;
import entities.company.Company;
import enums.ContractType;
import enums.EmployeeTitle;
import enums.EmploymentType;
import enums.Gender;
import enums.TaxStatus;
import exceptions.EmployeeContractTypeException;
import exceptions.EmployeeTaxStatusException;
import exceptions.EmployeeTitleException;
import exceptions.EmploymentTypeException;
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
public class EmployeeFromXml implements EmployeeProvider, RandomEmployeeProvider {
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
		NodeList emps = getEmployees();
	  for (int idx = 0; idx < emps.getLength(); idx++) {
	  	Node n = emps.item(idx);
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		Element el = (Element) n;	  		
	  		String id = el.getAttribute("id");
	  		if(id.equals(recordId)) {
	  			logger.info("Getting employee XML record [" + id + "]");
	  			mapRequired(n);
	  			mapTheRest(n);
	  			break;
	  		}	  		
	  	}
	  }	  
		return emp;
	}
	
	@Override // EmployeeProvider
	public Employee getEmployeeWithRequiredFields(String recordId) {
		NodeList emps = getEmployees();
	  for (int idx = 0; idx < emps.getLength(); idx++) {
	  	Node n = emps.item(idx);
	  	if (n.getNodeType() == Node.ELEMENT_NODE) {
	  		Element el = (Element) n;	  		
	  		String id = el.getAttribute("id");
	  		if(id.equals(recordId)) {
	  			logger.info("Getting employee XML record [" + id + "]");
	  			mapRequired(n);
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
		Element e = (Element) n;
		mapRequired(e);
		mapTheRest(e);		
	}
	
	private String getContent(Element e, String tagName) {
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
//	private void mapRequired(Element required) {		
	private void mapRequired(Node n) {
		getEmp(); //return, not set emp!
		EmployeeRequiredFieldsMapper mapper = new EmployeeRequiredFieldsMapper();
		mapper.mapRequired(emp, n);
		
//		Element required = (Element) n;
//		NodeList nodes = required.getElementsByTagName("Required");		
//		Element e = (Element) nodes.item(0);
//		
//		getEmp();
//		EmployeeRequired empWithRequiredFields = emp;		
//		empWithRequiredFields
//			.setEmpCode(getContent(e, "Code"))
//			.setFirstName(getContent(e, "FirstName"))
//			.setLastName(getContent(e, "Surname"))
//			.setIdCardNumber(getContent(e, "IdCardNumber"))								
//			.setAddressOne(getContent(e, "AddressOne"))
//			.setTown(getContent(e, "Town"))
//			.setCountry(getContent(e, "Country"))
//			.setGender(getGender(getContent(e, "Gender")))
//			.setDateOfBirth(getContent(e, "DOB"))
//			.setDateOfEmployement(getContent(e, "DOE"))
//			.setTaxNumber(getContent(e, "TaxNumber"))
//			.setNiNumber(getContent(e, "NiNumber"))	
//			.setPayGroup(getContent(e, "PayGroup"))
//			.setAnnualSalary(getAsCurrency(getContent(e, "AnnualSalary")))								
//			.setHourlyRate(getAsCurrency(getContent(e, "HourlyRate")));		
	}

//	private void mapTheRest(Element optional) {
	private void mapTheRest(Node n) {
		Element optional = (Element) n;
		NodeList nodes = optional.getElementsByTagName("Optional");		
		Element e = (Element) nodes.item(0);
		
		getEmp();
		EmployeeOptional empWithOptFields = emp;
		empWithOptFields
			.setStreet(getContent(e, "Street"))
			.setPostCode(getContent(e, "PostCode"))
			.setBank(getContent(e, "Bank"))
			.setIbanNumber(getContent(e, "IBAN"))
			.setEmailAddress(getContent(e, "EmailAddress"))
			.setMobileNumber(getContent(e, "MobileNumber"))				
			.setCompany(getCompany(getContent(e, "Company")))
			.setDepartment(getContent(e, "Department"))
			.setSchedule(getContent(e, "Schedule"))			
			.setSpecialPartTimer(getIsSpecialPartTimer(getContent(e, "IsSpecialPartTimer")))
			.setGrade(getContent(e, "Grade"))
			.setCostCentre(getContent(e, "Grade"));
//		empWithOptFields.setTaxablePermanentAllowances(getContent(e, "TaxablePermanentAllowances"));
//		empWithOptFields.setNonTaxablePermanentAllowances(getContent(e, "NonTaxablePermanentAllowances"));
		
		try {
			empWithOptFields.setEmployeeTitle(getEmployeeTitle(getContent(e, "Title")));			
		} catch (EmployeeTitleException e1) {
			// Nothing. Have reported the error.
		}
		try {
			empWithOptFields.setTaxStatus(getEmployeeTaxStatus(getContent(e, "TaxStatus")));			
		} catch (EmployeeTaxStatusException e1) {
			// Nothing. Have reported the error.
		}
		try {
			empWithOptFields.setContractType(getContractType(getContent(e, "ContractType")));			
		} catch (EmployeeContractTypeException e1) {
			// Nothing. Have reported the error.
		}
		try {
			empWithOptFields.setEmploymentType(getEmploymentType(getContent(e, "EmploymentType")));			
		} catch (EmploymentTypeException e1) {
			// Nothing. Have reported the error.
		}
	}
	
	private void getEmp() {
		if(emp == null) {
			emp = new Employee();
		}
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
	
	private EmployeeTitle getEmployeeTitle(String t) throws EmployeeTitleException{
		if(t != null) {
			if(t.equalsIgnoreCase("mrs")) {
				return EmployeeTitle.MRS;
			}else if (t.equalsIgnoreCase("mr")) {
				return EmployeeTitle.MR;
			}else if (t.equalsIgnoreCase("ms")) {
				return EmployeeTitle.MS;
			}else if (t.equalsIgnoreCase("prof")) {
				return EmployeeTitle.PROF;
			}else if (t.equalsIgnoreCase("sir")) {
					return EmployeeTitle.SIR;
			}else if (t.equalsIgnoreCase("dr")) {
					return EmployeeTitle.DR;				
			}else {
				throw new EmployeeTitleException("Incompatible title [" + t + "]");
			}	
		}else {
			throw new EmployeeTitleException("Title is [NULL]");
		}		
	}
	
	private TaxStatus getEmployeeTaxStatus(String t) throws EmployeeTaxStatusException{
		if(t != null) {
			if(t.equalsIgnoreCase("m") || t.equalsIgnoreCase("married")) {
				return TaxStatus.Married;
			}else if (t.equalsIgnoreCase("s") || t.equalsIgnoreCase("single")) {
				return TaxStatus.Single;
			}else if (t.equalsIgnoreCase("p") || t.equalsIgnoreCase("parental")) {
				return TaxStatus.Parent;				
			}else {
				throw new EmployeeTaxStatusException("Incompatible tax status [" + t + "]");
			}	
		}else {
			throw new EmployeeTaxStatusException("Tax status is [NULL]");
		}		
	}
	
	private ContractType getContractType(String t) throws EmployeeContractTypeException {
		if(t != null) {
			if(t.equalsIgnoreCase("definite contract")) {
				return ContractType.DEFINITE;
			}else if (t.equalsIgnoreCase("definite contract")) {
				return ContractType.INDEFINITE;
			}else if (t.equalsIgnoreCase("special contract")) {
				return ContractType.SPECIAL;
			}else if (t.equalsIgnoreCase("secondment")) {
				return ContractType.SECONDMENT;
			}else if (t.equalsIgnoreCase("student")) {
					return ContractType.STUDENT;
			}else if (t.equalsIgnoreCase("sponsor")) {
					return ContractType.SPONSOR;				
			}else {
				throw new EmployeeContractTypeException("Incompatible contract type [" + t + "]");
			}	
		}else {
			throw new EmployeeContractTypeException("Contract type is [NULL]");
		}		
	}
	
	private EmploymentType getEmploymentType(String t) throws EmploymentTypeException{
		if(t != null) {
			if(t.equalsIgnoreCase("full") || t.equalsIgnoreCase("full time")) {
				return EmploymentType.FULL_TIME;
			}else if (t.equalsIgnoreCase("part") || t.equalsIgnoreCase("part time")) {
				return EmploymentType.PART_TIME;				
			}else {
				throw new EmploymentTypeException("Incompatible employment type [" + t + "]");
			}	
		}else {
			throw new EmploymentTypeException("Employement type is [NULL]");
		}		
	}
	
	private Company getCompany(String compName) {
		return new Company(compName);
	}
	
	private boolean getIsSpecialPartTimer(String s) {
		if(s == null || s.equalsIgnoreCase("F")) {
			return false;
		}else {
			return true;
		}
	}
	private BigDecimal getAsCurrency(String val) {
		return BigDecimal.valueOf(Double.valueOf(val));
	}
	
}
