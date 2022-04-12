/**
 * 
 */
package providers.employee;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import content.EmployeeContent;
import content.EmployeeTestData;
import content_getter.SiteMapContentGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Implement getEmployeeAll(...). 
 * @since 1.0
 *
 */
public class EmployeeFromXml {//implements EmployeeProvider, RandomEmployeeProvider {
	private List<EmployeeContent> content;
	
	@SuppressWarnings("unused")
	private final Logger LOGGER = LogManager.getLogger(EmployeeFromXml.class);
	
	public EmployeeFromXml(final String XML_PATH) {
		SiteMapContentGetter<EmployeeTestData> contentGetter = 
			new SiteMapContentGetter<>(
				XML_PATH, EmployeeTestData.class);
			
		contentGetter.getContent().ifPresent(c -> {
			content = c.getEmployees();
		});
	}
	
	public EmployeeContent getFirstEmployee() {
		EmployeeContent res = null;
		if(content != null) {
			res = content.get(0);
		}
		return res;
	}
	public EmployeeContent getFirstEmployeeWithCompleteContent() {
		EmployeeContent res = null;
		if(content != null) {
			for(EmployeeContent c : content) {
				if(c.getRequired() != null && c.getOptional() != null) {
					res = c;
					break;
				}
			}			
		}
		return res;
	}
//	@Override // RandomEmployeeProvider
	public EmployeeContent getAnyEmpWithRandomCode() {
		EmployeeContent emp = getFirstEmployeeWithCompleteContent();
		if(emp != null) {
			emp.setEmpCode(getRandomCode());
		}
		return emp;
	}
	
	private String getRandomCode() {
		String code = "";
		
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			code += (char)(r.nextInt(26) + 'a'); 
		}
		return code;
	}
	
//	@Override // EmployeeProvider
//	public Employee getEmployeeW?ithAllFields(String recordId) {		
//		return mapFields(
//				recordId, 
//				Arrays.asList(
//						new EmployeeRequiredFieldsMapper(getEmp()), 
//						new EmployeeOptionalFieldsMapper(getEmp())));
//	}
	
//	@Override // EmployeeProvider
//	public Employee getEmployeeWithRequiredFields(String recordId) {
//		return mapFields(
//				recordId, 
//				Arrays.asList(
//						new EmployeeRequiredFieldsMapper(getEmp())));
//	}
			
}
