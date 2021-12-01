/**
 * 
 */
package object_models.pages.homepage;

import entities.Company;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class HomePagePersonnel extends HomePage {

	public HomePagePersonnel(CoreData coreData, Company co) {
		super(coreData, co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getModuleName() {
		return "Payroll";
	}
}
