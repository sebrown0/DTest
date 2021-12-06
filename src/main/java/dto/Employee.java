/**
 * 
 */
package dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entities.company.Company;
import enums.ContractType;
import enums.EmployeeTitle;
import enums.EmploymentType;
import enums.Gender;
import enums.TaxStatus;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 * 	
 * @since 1.0
 *
 */
public class Employee implements EmployeeRequired, EmployeeOptional {
	//Required
	private String firstName; 
	private String lastName;
	private String empCode;
	private BigDecimal annualSalary;
	private BigDecimal hourlyRate;
	private String idCardNumber;	
	private String addressOne;
	private String town;
	private String country;
	private Gender gender;
	private String dateOfBirth;
	private String dateOfEmployement;
	private String taxNumber;
	private String niNumber;
	private String payGroup;
	
	//Optional
	private String street;
	private String postCode;
	private String bank;
	private String ibanNumber;
	private String emailAddress;
	private String mobileNumber;
	private TaxStatus taxStatus;
	private ContractType contractType;
	private Company Company;
	private String department;
	private String schedule;
	private EmploymentType employmentType;
	private EmployeeTitle employeeTitle;
	private boolean isSpecialPartTimer;
	private String grade;
	private String costCentre;
	private List<Allowance> taxablePermanentAllowances = new ArrayList<>();
	private List<Allowance> nonTaxablePermanentAllowances = new ArrayList<>();
	
	// Employee/Helpers
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public String getFormalName() {
		return lastName + " " + firstName;
	}
	
	// Required 
	@Override //RequiredFields
	public String getFirstName() {
		return firstName;
	}
	@Override //RequiredFields
	public EmployeeRequired setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	@Override //RequiredFields
	public String getLastName() {
		return lastName;
	}
	@Override //RequiredFields
	public EmployeeRequired setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	@Override //RequiredFields
	public String getEmpCode() {
		return empCode;
	}
	@Override //RequiredFields
	public EmployeeRequired setEmpCode(String empCode) {
		this.empCode = empCode;
		return this;
	}
	@Override //RequiredFields
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	@Override //RequiredFields
	public EmployeeRequired setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
		return this;
	}
	@Override //RequiredFields
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}
	@Override //RequiredFields
	public EmployeeRequired setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
		return this;
	}
	@Override //RequiredFields
	public String getIdCardNumber() {
		return idCardNumber;
	}
	@Override //RequiredFields
	public EmployeeRequired setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
		return this;
	}
	@Override //RequiredFields
	public String getAddressOne() {
		return addressOne;
	}
	@Override //RequiredFields
	public EmployeeRequired setAddressOne(String addressOne) {
		this.addressOne = addressOne;
		return this;
	}
	@Override //RequiredFields
	public String getTown() {
		return town;
	}
	@Override //RequiredFields
	public EmployeeRequired setTown(String town) {
		this.town = town;
		return this;
	}
	@Override //RequiredFields
	public String getCountry() {
		return country;
	}
	@Override //RequiredFields
	public EmployeeRequired setCountry(String country) {
		this.country = country;
		return this;
	}
	@Override //RequiredFields
	public Gender getGender() {
		return gender;
	}
	@Override //RequiredFields
	public EmployeeRequired setGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	@Override //RequiredFields
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	@Override //RequiredFields
	public EmployeeRequired setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	@Override //RequiredFields
	public String getDateOfEmployement() {
		return dateOfEmployement;
	}
	@Override //RequiredFields
	public EmployeeRequired setDateOfEmployement(String dateOfEmployement) {
		this.dateOfEmployement = dateOfEmployement;
		return this;
	}
	@Override //RequiredFields
	public String getTaxNumber() {
		return taxNumber;
	}
	@Override //RequiredFields
	public EmployeeRequired setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
		return this;
	}
	@Override //RequiredFields
	public String getNiNumber() {
		return niNumber;
	}
	@Override //RequiredFields
	public EmployeeRequired setNiNumber(String niNumber) {
		this.niNumber = niNumber;
		return this;
	}
	@Override //RequiredFields
	public String getPayGroup() {
		return payGroup;
	}
	@Override //RequiredFields
	public EmployeeRequired setPayGroup(String payGroup) {
		this.payGroup = payGroup;
		return this;
	}

	// Optional
	@Override //OptionalFields
	public String getStreet() {
		return street;
	}
	@Override //OptionalFields
	public EmployeeOptional setStreet(String street) {
		this.street = street;
		return this;
	}
	@Override //OptionalFields
	public String getPostCode() {
		return postCode;
	}
	@Override //OptionalFields
	public EmployeeOptional setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}
	@Override //OptionalFields
	public String getBank() {
		return bank;
	}
	@Override //OptionalFields
	public EmployeeOptional setBank(String bank) {
		this.bank = bank;
		return this;
	}
	@Override //OptionalFields
	public String getIbanNumber() {
		return ibanNumber;
	}
	@Override //OptionalFields
	public EmployeeOptional setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber;
		return this;
	}
	@Override //OptionalFields
	public String getEmailAddress() {
		return emailAddress;
	}
	@Override //OptionalFields
	public EmployeeOptional setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}
	@Override //OptionalFields
	public String getMobileNumber() {
		return mobileNumber;
	}
	@Override //OptionalFields
	public EmployeeOptional setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}
	@Override //OptionalFields
	public TaxStatus getTaxStatus() {
		return taxStatus;
	}
	@Override //OptionalFields
	public EmployeeOptional setTaxStatus(TaxStatus taxStatus) {
		this.taxStatus = taxStatus;
		return this;
	}
	@Override //OptionalFields
	public ContractType getContractType() {
		return contractType;
	}
	@Override //OptionalFields
	public EmployeeOptional setContractType(ContractType contractType) {
		this.contractType = contractType;
		return this;
	}
	@Override //OptionalFields
	public Company getCompany() {
		return Company;
	}
	@Override //OptionalFields
	public EmployeeOptional setCompany(Company company) {
		Company = company;
		return this;
	}
	@Override //OptionalFields
	public String getDepartment() {
		return department;
	}
	@Override //OptionalFields
	public EmployeeOptional setDepartment(String department) {
		this.department = department;
		return this;
	}
	@Override //OptionalFields
	public String getSchedule() {
		return schedule;
	}
	@Override //OptionalFields
	public EmployeeOptional setSchedule(String schedule) {
		this.schedule = schedule;
		return this;
	}
	@Override //OptionalFields
	public EmploymentType getEmploymentType() {
		return employmentType;
	}
	@Override //OptionalFields
	public EmployeeOptional setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
		return this;
	}
	@Override //OptionalFields
	public boolean isSpecialPartTimer() {
		return isSpecialPartTimer;
	}
	@Override //OptionalFields
	public EmployeeOptional setSpecialPartTimer(boolean isSpecialPartTimer) {
		this.isSpecialPartTimer = isSpecialPartTimer;
		return this;
	}
	@Override //OptionalFields
	public String getGrade() {
		return grade;
	}
	@Override //OptionalFields
	public EmployeeOptional setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	@Override //OptionalFields
	public String getCostCentre() {
		return costCentre;
	}
	@Override //OptionalFields
	public EmployeeOptional setCostCentre(String costCentre) {
		this.costCentre = costCentre;
		return this;
	}
	@Override //OptionalFields
	public List<Allowance> getTaxablePermanentAllowances() {
		return taxablePermanentAllowances;
	}
	@Override //OptionalFields
	public EmployeeOptional setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
		this.taxablePermanentAllowances = taxablePermanentAllowances;
		return this;
	}
	@Override //OptionalFields
	public List<Allowance> getNonTaxablePermanentAllowances() {
		return nonTaxablePermanentAllowances;
	}
	@Override //OptionalFields
	public EmployeeOptional setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
		this.nonTaxablePermanentAllowances = nonTaxablePermanentAllowances;
		return this;
	}
	@Override
	public EmployeeOptional setEmployeeTitle(EmployeeTitle employeeTitle) {
		this.employeeTitle = employeeTitle;
		return this;
	}
	@Override
	public EmployeeTitle getEmployeeTitle() {
		return employeeTitle;
	}
	
}