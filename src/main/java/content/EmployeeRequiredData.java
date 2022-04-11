/**
 * 
 */
package content;

import java.math.BigDecimal;

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
@XmlRootElement(name="Required", namespace = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRequiredData  {
	//Required
	@XmlElement(name="FirstName", namespace="EmpReqType")
	private String firstName;
	@XmlElement(name="Surname", namespace="EmpReqType")
	private String lastName;
	@XmlElement(name="Code", namespace="EmpReqType")
	private String empCode;
	@XmlElement(name="AnnualSalary", namespace="EmpReqType")
	private BigDecimal annualSalary;
	@XmlElement(name="HourlyRate", namespace="EmpReqType")
	private BigDecimal hourlyRate;
	@XmlElement(name="IdCardNumber", namespace="EmpReqType")
	private String idCardNumber;
	@XmlElement(name="AddressOne", namespace="EmpReqType")
	private String addressOne;
	@XmlElement(name="Town", namespace="EmpReqType")
	private String town;
	@XmlElement(name="Country", namespace="EmpReqType")
	private String country;
	@XmlElement(name="Gender", namespace="EmpReqType")	
	private String gender;	
	@XmlElement(name="DOB", namespace="EmpReqType")
	private String dateOfBirth;
	@XmlElement(name="DOE", namespace="EmpReqType")
	private String dateOfEmployement;
	@XmlElement(name="TaxNumber", namespace="EmpReqType")
	private String taxNumber;
	@XmlElement(name="NiNumber", namespace="EmpReqType")
	private String niNumber;
	@XmlElement(name="PayGroup", namespace="EmpReqType")
	private String payGroup;
		
	// Employee/Helpers
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public String getFormalName() {
		return lastName + " " + firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}	
	public void setFirstName(String firstName) {
		this.firstName = firstName;	
	}
	
	public String getLastName() {
		return lastName;
	}	
	public void setLastName(String lastName) {
		this.lastName = lastName;	
	}
	
	public String getEmpCode() {
		return empCode;
	}	
	public void setEmpCode(String empCode) {
		this.empCode = empCode;		
	}
	
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}	
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;		
	}
	
	public BigDecimal getHourlyRate() {
		return hourlyRate;
	}	
	public void setHourlyRate(BigDecimal hourlyRate) {
		this.hourlyRate = hourlyRate;		
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;		
	}
	
	
	public String getAddressOne() {
		return addressOne;
	}	
	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;		
	}
	
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;		
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;		
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;		
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;		
	}
	
	public String getDateOfEmployement() {
		return dateOfEmployement;
	}	
	public void setDateOfEmployement(String dateOfEmployement) {
		this.dateOfEmployement = dateOfEmployement;		
	}
	
	public String getTaxNumber() {
		return taxNumber;
	}	
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;	
	}

	public String getNiNumber() {
		return niNumber;
	}	
	public void setNiNumber(String niNumber) {
		this.niNumber = niNumber;		
	}
	
	public String getPayGroup() {
		return payGroup;
	}	
	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}
	
}