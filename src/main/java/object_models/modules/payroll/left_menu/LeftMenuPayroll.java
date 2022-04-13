package object_models.modules.payroll.left_menu;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import object_models.common.nav.LeftMenuElements;

import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.modules.payroll.left_menu.employees.ContactNumbers;
/**
* Generated Class.
* ----------------
* Source:  C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 12/04/2022 14:28:39
*/

public class LeftMenuPayroll implements LeftMenuElements {
	private static final List<String> EMPLOYEES = Arrays.asList(
		ContactNumbers.MENU_TITLE
	);
	public List<String> getEmployees() {
		return EMPLOYEES;
	}
		@SuppressWarnings("unchecked")
		public Map<String, Optional<List<String>>> getAll(){
			return Stream.of(new Object[][] {
				{"Employees", Optional.of(EMPLOYEES)}
			}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		
		}
}