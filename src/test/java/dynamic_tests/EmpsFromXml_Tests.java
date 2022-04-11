package dynamic_tests;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import content.EmployeeTestData;
import content_getter.SiteMapContentGetter;

class EmpsFromXml_Tests {

	@Test
	void test() {
		SiteMapContentGetter<EmployeeTestData> contentGetter = 
			new SiteMapContentGetter<>(
				"C:/DakarHrXml/XML/employee/MargeAndHomer.xml", EmployeeTestData.class);
		
		Optional<EmployeeTestData> emps = contentGetter.getContent();
		System.out.println("->"); // TODO - remove or log 	
	}

}
