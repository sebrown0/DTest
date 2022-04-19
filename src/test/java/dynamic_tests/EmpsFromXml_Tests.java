package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import content_getter.SiteMapContentGetter;
import zzz_content.zzz_EmployeeContent;
import zzz_content.zzz_EmployeeTestData;

class EmpsFromXml_Tests {

	@Test
	void test() {
		SiteMapContentGetter<zzz_EmployeeTestData> contentGetter = 
			new SiteMapContentGetter<>(
				"C:/DakarHrXml/XML/employee/MargeAndHomer.xml", zzz_EmployeeTestData.class);
		
		Optional<zzz_EmployeeTestData> emps = contentGetter.getContent();

		zzz_EmployeeTestData data = emps.get();
		List<zzz_EmployeeContent> content = data.getEmployees();
		
		assertTrue(content.get(0).getFirstName() != null);
	}

}
