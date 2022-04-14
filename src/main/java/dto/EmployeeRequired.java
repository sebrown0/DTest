/**
 * 
 */
package dto;

import java.math.BigDecimal;

import library.dakar_hr.enums.Gender;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface EmployeeRequired {
	 String getFirstName();
	 EmployeeRequired setFirstName(String firstName); 
	 String getLastName(); 
	 EmployeeRequired setLastName(String lastName); 
	 String getEmpCode(); 
	 EmployeeRequired setEmpCode(String empCode); 
	 BigDecimal getAnnualSalary(); 
	 EmployeeRequired setAnnualSalary(BigDecimal annualSalary); 
	 BigDecimal getHourlyRate(); 
	 EmployeeRequired setHourlyRate(BigDecimal hourlyRate); 
	 String getIdCardNumber(); 
	 EmployeeRequired setIdCardNumber(String idCardNumber); 
	 String getAddressOne(); 
	 EmployeeRequired setAddressOne(String addressOne); 
	 String getTown(); 
	 EmployeeRequired setTown(String town); 
	 String getCountry(); 
	 EmployeeRequired setCountry(String country); 
	 Gender getGender();	 
	 EmployeeRequired setGender(Gender gender); 
	 String getDateOfBirth(); 
	 EmployeeRequired setDateOfBirth(String dateOfBirth); 
	 String getDateOfEmployement(); 
	 EmployeeRequired setDateOfEmployement(String dateOfEmployement); 
	 String getTaxNumber(); 
	 EmployeeRequired setTaxNumber(String taxNumber); 
	 String getNiNumber(); 
	 EmployeeRequired setNiNumber(String niNumber); 
	 String getPayGroup(); 
	 EmployeeRequired setPayGroup(String payGroup); 

}
