/**
 * 
 */
package site_mapper.creators;

import java.util.Optional;

import exceptions.InvalidArgumentException;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get a string that can be added to a 
 * class file as the java code for creating
 * a new ControlData object, i.e.
 * 	"new ControlData(
 * 		"control name", 
 * 		new ControlGetterTextOut(coreData, By.id("FORM_ID")))"
 * 
 */
public class ControlDataFactory {
//	private static String cd = "";
	/**
	 * @param controlTypeName
	 * 	button, text box etc
	 * @param byValue
	 * 	the value to be used to find the element in the DOM, 'By'.
	 * @param byType
	 * 	the type of 'By', i.e. CSS, XPATH or ID.
	 * @return: 
	 * 	a string to create a ControlData object
	 * @throws InvalidArgumentException 
	 */
	public static String getControlData(
			String controlName, String controlTypeName, String byValue, String byType) 
					throws InvalidArgumentException {
		
		String cd = "";
		Optional<String> actualType = checkByType(byType);
		if(actualType.isPresent()) {
			cd = switch (controlTypeName) {
				case "button" -> getControlDataStr(controlName, "ControlGetterButton", byValue, actualType.get());
				default -> throw new InvalidArgumentException("[" + controlTypeName + "] is not a valid control type name.");
			};	
		}else {
			throw new InvalidArgumentException("[" + byType + "] is not a valid By type name.");
		}					
		return cd;
	}

	private static Optional<String> checkByType(String byType) {
		String ret = null;
		if(byType.equalsIgnoreCase("CSS")) {
			ret = "By.cssSelector";
		}else if(byType.equalsIgnoreCase("XPATH")){
			ret = "By.xpath";
		}else if(byType.equalsIgnoreCase("ID") ) {
			ret = "By.id";
		}
		return Optional.ofNullable(ret);
	}
	
	private static String getControlDataStr(String controlName, String controlGetter, String byValue, String byActualType) {
		String str = 
				"new ControlData(" + 
				controlName + ", new " +
				controlGetter + "(coreData, " +
				byActualType +"(\"" +
				byValue;

		return str += "\")))";
	}
	
	/*
	 *
		new ControlData(EmployeeControlNames.EMP_NAME, new ControlGetterTextOut(coreData, By.xpath("/html/body/form/div[3]/div[3]/div[2]/input"))),
		new ControlData(GroupControlNames.SELECT_EMP, new ControlGetterEmployeeSelection(coreData, By.cssSelector("i[class='fa fa-list']"))),
		new ControlData(GroupControlNames.COMBOS, new ControlGetterDropdownCombo(coreData, By.cssSelector("i[class='fa fa-window-maximize']"))),
		new ControlData(GroupControlNames.GRID_VIEW, new ControlGetterDkGridEmployeeDetails(coreData, By.cssSelector("i[class='fa fw fa-table']"))),
		new ControlData(GroupControlNames.SAVE, new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
		new ControlData(GroupControlNames.SEARCH, new ControlGetterButton(coreData, By.cssSelector("button[name='QBF1']")))
	 */

}
