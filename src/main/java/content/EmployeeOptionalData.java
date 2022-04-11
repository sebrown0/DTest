/**
 * 
 */
package content;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entities.company.Company;
import enums.ContractType;
import enums.EmployeeTitle;
import enums.EmploymentType;
import enums.Gender;
import enums.TaxStatus;
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
@XmlRootElement(name="Optional", namespace = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeOptionalData  {	
	@XmlElement(name="Title",namespace="EmpOptType")
	private String title;
	@XmlElement(name="Street",namespace="EmpOptType")
	private String street;
	@XmlElement(name="PostCode",namespace="EmpOptType")
	private String postCode;
	@XmlElement(name="Bank",namespace="EmpOptType")
	private String bank;
	@XmlElement(name="IBAN",namespace="EmpOptType")
	private String ibanNumber;
	@XmlElement(name="EmailAddress",namespace="EmpOptType")
	private String emailAddress;
	@XmlElement(name="MobileNumber",namespace="EmpOptType")
	private String mobileNumber;
	@XmlElement(name="TaxStatus",namespace="EmpOptType")
	private String taxStatus;	
	@XmlElement(name="ContractType",namespace="EmpOptType")
	private String contractType;	
	@XmlElement(name="Company",namespace="EmpOptType")
	private String Company;
	@XmlElement(name="Department",namespace="EmpOptType")
	private String department;
	@XmlElement(name="Schedule",namespace="EmpOptType")
	private String schedule;
	@XmlElement(name="EmploymentType",namespace="EmpOptType")
	private String employmentType;
	@XmlElement(name="EmploymentTitle",namespace="EmpOptType")
	private String employeeTitle;	
	@XmlElement(name="IsSpecialPartTimer",namespace="EmpOptType")
	private boolean isSpecialPartTimer;
	@XmlElement(name="Grade",namespace="EmpOptType")
	private String grade;	
	@XmlElement(name="CostCenter",namespace="EmpOptType")
	private String costCentre;
	
//	private List<Allowance> Z_taxablePermanentAllowances = new ArrayList<>();
//	private List<Allowance> Z_nonTaxablePermanentAllowances = new ArrayList<>();
	
	
//	// Optional
//	@Override //OptionalFields
//	public String getStreet() {
//		return street;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setStreet(String street) {
//		this.street = street;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getPostCode() {
//		return postCode;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setPostCode(String postCode) {
//		this.postCode = postCode;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getBank() {
//		return bank;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setBank(String bank) {
//		this.bank = bank;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getIbanNumber() {
//		return ibanNumber;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setIbanNumber(String ibanNumber) {
//		this.ibanNumber = ibanNumber;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getEmailAddress() {
//		return emailAddress;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setEmailAddress(String emailAddress) {
//		this.emailAddress = emailAddress;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getMobileNumber() {
//		return mobileNumber;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setMobileNumber(String mobileNumber) {
//		this.mobileNumber = mobileNumber;
//		return this;
//	}
//	@Override //OptionalFields
//	public TaxStatus getTaxStatus() {
//		return taxStatus;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setTaxStatus(TaxStatus taxStatus) {
//		this.taxStatus = taxStatus;
//		return this;
//	}
//	@Override //OptionalFields
//	public ContractType getContractType() {
//		return contractType;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setContractType(ContractType contractType) {
//		this.contractType = contractType;
//		return this;
//	}
//	@Override //OptionalFields
//	public Company getCompany() {
//		return Company;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setCompany(Company company) {
//		Company = company;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getDepartment() {
//		return department;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setDepartment(String department) {
//		this.department = department;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getSchedule() {
//		return schedule;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setSchedule(String schedule) {
//		this.schedule = schedule;
//		return this;
//	}
//	@Override //OptionalFields
//	public EmploymentType getEmploymentType() {
//		return employmentType;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setEmploymentType(EmploymentType employmentType) {
//		this.employmentType = employmentType;
//		return this;
//	}
//	@Override //OptionalFields
//	public boolean isSpecialPartTimer() {
//		return isSpecialPartTimer;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setSpecialPartTimer(boolean isSpecialPartTimer) {
//		this.isSpecialPartTimer = isSpecialPartTimer;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getGrade() {
//		return grade;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setGrade(String grade) {
//		this.grade = grade;
//		return this;
//	}
//	@Override //OptionalFields
//	public String getCostCentre() {
//		return costCentre;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setCostCentre(String costCentre) {
//		this.costCentre = costCentre;
//		return this;
//	}
//	@Override //OptionalFields
//	public List<Allowance> getTaxablePermanentAllowances() {
//		return Z_taxablePermanentAllowances;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
//		this.Z_taxablePermanentAllowances = taxablePermanentAllowances;
//		return this;
//	}
//	@Override //OptionalFields
//	public List<Allowance> getNonTaxablePermanentAllowances() {
//		return Z_nonTaxablePermanentAllowances;
//	}
//	@Override //OptionalFields
//	public EmployeeOptional setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
//		this.Z_nonTaxablePermanentAllowances = nonTaxablePermanentAllowances;
//		return this;
//	}
//	@Override
//	public EmployeeOptional setEmployeeTitle(EmployeeTitle employeeTitle) {
//		this.employeeTitle = employeeTitle;
//		return this;
//	}
//	@Override
//	public EmployeeTitle getEmployeeTitle() {
//		return employeeTitle;
//	}
	
}