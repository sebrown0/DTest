/**
 * 
 */
package object_models.pages.homepage;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public final class HomePagePersonnel extends HomePage {

	public HomePagePersonnel(CoreData coreData) {
		super(coreData);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getModuleName() {
		return "Payroll";
	}
}
