package zzz_content;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Employees", namespace = "EmpTestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class zzz_EmployeeTestData {
	@XmlElement(name = "Employee", namespace = "EmpTestData")
	private List<zzz_EmployeeContent> employees;

	public List<zzz_EmployeeContent> getEmployees() {
		return employees;
	}

	public void setEmployees(List<zzz_EmployeeContent> employees) {
		this.employees = employees;
	}
		
}
