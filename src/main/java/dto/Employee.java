/**
 * 
 */
package dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import enums.ContractType;
import enums.EmploymentType;
import enums.Gender;
import enums.TaxStatus;

/**
 * @author Steve Brown
 *
 */
public class Employee {
	private String firstName; 
	private String lastName;
	private String idCardNumber;
	private String empCode;
	private String addressOne;
	private String street;
	private String postCode;	
	private String town;
	private String country;
	private Gender gender;
	private String dateOfBirth;
	private String dateOfEmployement;
	private String taxNumber;
	private String niNumber;
	private String bank;
	private String ibanNumber;
	private String emailAddress;
	private String mobileNumber;
	private TaxStatus taxStatus;
	private ContractType contractType;
	private String Company;
	private String payGroup;
	private String department;
	private String schedule;
	private EmploymentType employmentType;
	private boolean isSpecialPartTimer;
	private String grade;
	private String costCentre;
	private List<Allowance> taxablePermanentAllowances = new ArrayList<>();
	private List<Allowance> nonTaxablePermanentAllowances = new ArrayList<>();
	private BigDecimal annualSalary;
	private BigDecimal hourlyRate;
	private static RequiredFields requiredFields = new RequiredFields();
	
	public String getFirstName() {
		return firstName;
	}	
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public String getEmpCode() {
		return empCode;
	}
	public String getAddressOne() {
		return addressOne;
	}
	public String getStreet() {
		return street;
	}
	public String getPostCode() {
		return postCode;
	}
	public String getTown() {
		return town;
	}
	public String getCountry() {
		return country;
	}
	public Gender getGender() {
		return gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getDateOfEmployement() {
		return dateOfEmployement;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public String getNiNumber() {
		return niNumber;
	}
	public String getBank() {
		return bank;
	}
	public String getIbanNumber() {
		return ibanNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public TaxStatus getTaxStatus() {
		return taxStatus;
	}
	public ContractType getContractType() {
		return contractType;
	}
	public String getCompany() {
		return Company;
	}
	public String getPayGroup() {
		return payGroup;
	}
	public String getDepartment() {
		return department;
	}
	public String getSchedule() {
		return schedule;
	}
	public EmploymentType getEmploymentType() {
		return employmentType;
	}
	public boolean isSpecialPartTimer() {
		return isSpecialPartTimer;
	}
	public String getGrade() {
		return grade;
	}
	public String getCostCentre() {
		return costCentre;
	}
	public List<Allowance> getTaxablePermanentAllowances() {
		return taxablePermanentAllowances;
	}
	public List<Allowance> getNonTaxablePermanentAllowances() {
		return nonTaxablePermanentAllowances;
	}
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}
	
	public Employee setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public Employee setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public Employee setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
		return this;
	}
	public Employee setEmpCode(String empCode) {
		this.empCode = empCode;
		return this;
	}
	public Employee setAddressOne(String addressOne) {
		this.addressOne = addressOne;
		return this;
	}
	public Employee setStreet(String street) {
		this.street = street;
		return this;
	}
	public Employee setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}
	public Employee setTown(String town) {
		this.town = town;
		return this;
	}
	public Employee setCountry(String country) {
		this.country = country;
		return this;
	}
	public Employee setGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	public Employee setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	public Employee setDateOfEmployement(String dateOfEmployement) {
		this.dateOfEmployement = dateOfEmployement;
		return this;
	}
	public Employee setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
		return this;
	}
	public Employee setNiNumber(String niNumber) {
		this.niNumber = niNumber;
		return this;
	}
	public Employee setBank(String bank) {
		this.bank = bank;
		return this;
	}
	public Employee setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber;
		return this;
	}
	public Employee setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}
	public Employee setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}
	public Employee setTaxStatus(TaxStatus taxStatus) {
		this.taxStatus = taxStatus;
		return this;
	}
	public Employee setContractType(ContractType contractType) {
		this.contractType = contractType;
		return this;
	}
	public Employee setCompany(String company) {
		Company = company;
		return this;
	}
	public Employee setPayGroup(String payGroup) {
		this.payGroup = payGroup;
		return this;
	}
	public Employee setDepartment(String department) {
		this.department = department;
		return this;
	}
	public Employee setSchedule(String schedule) {
		this.schedule = schedule;
		return this;
	}
	public Employee setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
		return this;
	}
	public Employee setSpecialPartTimer(boolean isSpecialPartTimer) {
		this.isSpecialPartTimer = isSpecialPartTimer;
		return this;
	}
	public Employee setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	public Employee setCostCentre(String costCentre) {
		this.costCentre = costCentre;
		return this;
	}
	public Employee setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
		this.taxablePermanentAllowances = taxablePermanentAllowances;
		return this;
	}
	public Employee setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
		this.nonTaxablePermanentAllowances = nonTaxablePermanentAllowances;
		return this;
	}
	public Employee setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
		return this;
	}
	public Employee setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;
		return this;
	}

	public static RequiredFields withRequiredFields() {
		return requiredFields;
	}
	
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", empCode=" + empCode + "]";
	}
	
	public static class RequiredFields {
		private Employee emp = new Employee();
		
		public RequiredFields firstName(String firstName) {
			emp.setFirstName(firstName);
			return this;
		}		
		public RequiredFields lastName(String lastName) {
			emp.setLastName(lastName);
			return this;
		}		
		public RequiredFields idCardNumber(String idCardNumber) {
			emp.setIdCardNumber(idCardNumber);
			return this;
		}
		public RequiredFields empCode(String empCode) {
			emp.setEmpCode(empCode);
			return this;
		}
		public RequiredFields addressOne(String addressOne) {
			emp.setAddressOne(addressOne);
			return this;
		}
		public RequiredFields town(String town) {
			emp.setTown(town);
			return this;
		}
		public RequiredFields country(String country) {
			emp.setCountry(country);
			return this;
		}
		public RequiredFields gender(Gender gender) {
			emp.setGender(gender);
			return this;
		}
		public RequiredFields dateOfBirth(String dateOfBirth) {
			emp.setDateOfBirth(dateOfBirth);
			return this;
		}
		public RequiredFields dateOfEmployement(String dateOfEmployement) {
			emp.setDateOfEmployement(dateOfEmployement);
			return this;
		}
		public RequiredFields taxNumber(String taxNumber) {
			emp.setTaxNumber(taxNumber);
			return this;
		}
		public RequiredFields niNumber(String niNumber) {
			emp.setNiNumber(niNumber);
			return this;
		}
		public RequiredFields annualSalary(BigDecimal annualSalary) {
			emp.setAnnualSalary(annualSalary);
			return this;
		}
		public RequiredFields hourlyRate(BigDecimal hourlyRate) {
			emp.setHourlyRate(hourlyRate);
			return this;
		}
		public RequiredFields payGroup(String payGroup) {
			emp.setPayGroup(payGroup);
			return this;
		}
				
		public Employee build() {
			return emp;
		}
	}
}
