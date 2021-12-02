/**
 * 
 */
package factories;

import org.apache.logging.log4j.LogManager;

import entities.Company;
import object_models.modules.common.ModuleElements;
import object_models.modules.payroll.PayrollModuleElements;
import object_models.modules.personnel.PersonnelModuleElements;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ModuleElementsFactory {
	public static ModuleElements getModuleElements(String moduleName, Company company) {
		ModuleElements elements = null;
		
		switch (moduleName) {
		case "Payroll":
			elements = new PayrollModuleElements(company);
			break;
		case "Personnel":
			elements = new PersonnelModuleElements(company);
			break;

		default:
			LogManager.getLogger().error("Not implemented for [" + moduleName + "]");
		}
		
		return elements;
	}
}
