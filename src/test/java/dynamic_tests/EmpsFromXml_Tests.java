package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import content.EmployeeContent;
import content.EmployeeTestData;
import content_getter.SiteMapContentGetter;

class EmpsFromXml_Tests {

	@Test
	void test() {
		SiteMapContentGetter<EmployeeTestData> contentGetter = 
			new SiteMapContentGetter<>(
				"C:/DakarHrXml/XML/employee/MargeAndHomer.xml", EmployeeTestData.class);
		
		Optional<EmployeeTestData> emps = contentGetter.getContent();

		EmployeeTestData data = emps.get();
		List<EmployeeContent> content = data.getEmployees();
		
		assertTrue(content.get(0).getFirstName() != null);
	}

}
