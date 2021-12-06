/**
 * 
 */
package providers.employee;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dto.EmployeeOptional;
import enums.Gender;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class EmployeeRequiredFieldsMapper extends EmployeeMapper {
private EmployeeOptional empWithRequiredFields;
	
	public EmployeeRequiredFieldsMapper(EmployeeOptional empWithRequiredFields) {
		this.empWithRequiredFields = empWithRequiredFields;
	}
	
	public void mapFields(Node n) {
		Element required = (Element) n;
		NodeList nodes = required.getElementsByTagName("Required");		
		Element e = (Element) nodes.item(0);
		
		empWithRequiredFields
			.setEmpCode(getContent(e, "Code"))
			.setFirstName(getContent(e, "FirstName"))
			.setLastName(getContent(e, "Surname"))
			.setIdCardNumber(getContent(e, "IdCardNumber"))								
			.setAddressOne(getContent(e, "AddressOne"))
			.setTown(getContent(e, "Town"))
			.setCountry(getContent(e, "Country"))
			.setGender(getGender(getContent(e, "Gender")))
			.setDateOfBirth(getContent(e, "DOB"))
			.setDateOfEmployement(getContent(e, "DOE"))
			.setTaxNumber(getContent(e, "TaxNumber"))
			.setNiNumber(getContent(e, "NiNumber"))	
			.setPayGroup(getContent(e, "PayGroup"))
			.setAnnualSalary(getAsCurrency(getContent(e, "AnnualSalary")))								
			.setHourlyRate(getAsCurrency(getContent(e, "HourlyRate")));		
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
}
