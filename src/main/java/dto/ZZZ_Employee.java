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
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class ZZZ_Employee {
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
	public String getFormalName() {
		return lastName + " " + firstName;
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
	
	public ZZZ_Employee setFirstName(String firstName) {
		this.firstName = firstName.trim();;
		return this;
	}
	public ZZZ_Employee setLastName(String lastName) {
		this.lastName = lastName.trim();
		return this;
	}
	public ZZZ_Employee setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber.trim();;
		return this;
	}
	public ZZZ_Employee setEmpCode(String empCode) {
		this.empCode = empCode.trim();;
		return this;
	}
	public ZZZ_Employee setAddressOne(String addressOne) {
		this.addressOne = addressOne.trim();;
		return this;
	}
	public ZZZ_Employee setStreet(String street) {
		this.street = street.trim();;
		return this;
	}
	public ZZZ_Employee setPostCode(String postCode) {
		this.postCode = postCode.trim();;
		return this;
	}
	public ZZZ_Employee setTown(String town) {
		this.town = town.trim();;
		return this;
	}
	public ZZZ_Employee setCountry(String country) {
		this.country = country.trim();;
		return this;
	}
	public ZZZ_Employee setGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	public ZZZ_Employee setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth.trim();;
		return this;
	}
	public ZZZ_Employee setDateOfEmployement(String dateOfEmployement) {
		this.dateOfEmployement = dateOfEmployement.trim();;
		return this;
	}
	public ZZZ_Employee setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber.trim();;
		return this;
	}
	public ZZZ_Employee setNiNumber(String niNumber) {
		this.niNumber = niNumber.trim();;
		return this;
	}
	public ZZZ_Employee setBank(String bank) {
		this.bank = bank.trim();;
		return this;
	}
	public ZZZ_Employee setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber.trim();;
		return this;
	}
	public ZZZ_Employee setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress.trim();;
		return this;
	}
	public ZZZ_Employee setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber.trim();;
		return this;
	}
	public ZZZ_Employee setTaxStatus(TaxStatus taxStatus) {
		this.taxStatus = taxStatus;
		return this;
	}
	public ZZZ_Employee setContractType(ContractType contractType) {
		this.contractType = contractType;
		return this;
	}
	public ZZZ_Employee setCompany(String company) {
		Company = company.trim();;
		return this;
	}
	public ZZZ_Employee setPayGroup(String payGroup) {
		this.payGroup = payGroup.trim();;
		return this;
	}
	public ZZZ_Employee setDepartment(String department) {
		this.department = department.trim();;
		return this;
	}
	public ZZZ_Employee setSchedule(String schedule) {
		this.schedule = schedule.trim();;
		return this;
	}
	public ZZZ_Employee setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
		return this;
	}
	public ZZZ_Employee setSpecialPartTimer(boolean isSpecialPartTimer) {
		this.isSpecialPartTimer = isSpecialPartTimer;
		return this;
	}
	public ZZZ_Employee setGrade(String grade) {
		this.grade = grade.trim();;
		return this;
	}
	public ZZZ_Employee setCostCentre(String costCentre) {
		this.costCentre = costCentre.trim();;
		return this;
	}
	public ZZZ_Employee setTaxablePermanentAllowances(List<Allowance> taxablePermanentAllowances) {
		this.taxablePermanentAllowances = taxablePermanentAllowances;
		return this;
	}
	public ZZZ_Employee setNonTaxablePermanentAllowances(List<Allowance> nonTaxablePermanentAllowances) {
		this.nonTaxablePermanentAllowances = nonTaxablePermanentAllowances;
		return this;
	}
	public ZZZ_Employee setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
		return this;
	}
	public ZZZ_Employee setHourlyRate(BigDecimal hourlyRate) {
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
		private ZZZ_Employee emp = new ZZZ_Employee();
		
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
				
		public ZZZ_Employee build() {
			return emp;
		}
	}

}
