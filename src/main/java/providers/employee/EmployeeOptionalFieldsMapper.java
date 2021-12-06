/**
 * 
 */
package providers.employee;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.EmployeeOptional;
import entities.company.Company;
import enums.ContractType;
import enums.EmployeeTitle;
import enums.EmploymentType;
import enums.TaxStatus;
import exceptions.EmployeeContractTypeException;
import exceptions.EmployeeTaxStatusException;
import exceptions.EmployeeTitleException;
import exceptions.EmploymentTypeException;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class EmployeeOptionalFieldsMapper extends EmployeeMapper {
	private EmployeeOptional empWithOptFields;
	
	public EmployeeOptionalFieldsMapper(EmployeeOptional empWithOptFields) {
		this.empWithOptFields = empWithOptFields;
	}

	public void mapFields(Node n) {
		Element required = (Element) n;
		NodeList nodes = required.getElementsByTagName("Optional");		
		Element e = (Element) nodes.item(0);
		
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
}
