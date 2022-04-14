/**
 * 
 */
package dto;

import java.util.List;

import library.dakar_hr.entities.company.Company;
import library.dakar_hr.enums.ContractType;
import library.dakar_hr.enums.EmployeeTitle;
import library.dakar_hr.enums.EmploymentType;
import library.dakar_hr.enums.TaxStatus;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public interface EmployeeOptional extends EmployeeRequired{	
	String getStreet();
	EmployeeOptional setStreet(String street); 
	String getPostCode();
	EmployeeOptional setPostCode(String postCode);
	String getBank();
	EmployeeOptional setBank(String bank);
	String getIbanNumber();
	EmployeeOptional setIbanNumber(String ibanNumber);
	String getEmailAddress();
	EmployeeOptional setEmailAddress(String emailAddress);
	String getMobileNumber();
	EmployeeOptional setMobileNumber(String mobileNumber);
	TaxStatus getTaxStatus();
//	TaxStatus getTaxStatus();
	EmployeeOptional setTaxStatus(TaxStatus taxStatus);
	ContractType getContractType();
//	String getContractType();
	EmployeeOptional setEmployeeTitle(EmployeeTitle employeeTitle);
	EmployeeTitle getEmployeeTitle();
//	String getEmployeeTitle();
	EmployeeOptional setContractType(ContractType contractType);
	Company getCompany();
	EmployeeOptional setCompany(Company company);
	String getDepartment();
	EmployeeOptional setDepartment(String department);
	String getSchedule();
	EmployeeOptional setSchedule(String schedule);
	EmploymentType getEmploymentType();
//	String getEmploymentType();
	EmployeeOptional setEmploymentType(EmploymentType employmentType); 
	boolean isSpecialPartTimer();
	EmployeeOptional setSpecialPartTimer(boolean isSpecialPartTimer);
	String getGrade();
	EmployeeOptional setGrade(String grade);
	String getCostCentre();
	EmployeeOptional setCostCentre(String costCentre);
	List<Allowance> getTaxablePermanentAllowances();
	EmployeeOptional setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances);
	List<Allowance> getNonTaxablePermanentAllowances();
	EmployeeOptional setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances);
}
