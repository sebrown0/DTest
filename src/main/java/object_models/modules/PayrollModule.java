/**
 * 
 */
package object_models.modules;

import org.openqa.selenium.WebDriver;

import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 */
public class PayrollModule extends DakarModule{

	public PayrollModule(WebDriver driver) {
		super(driver, new PayrollModuleLoader(driver));
	}

	@Override
	public String getName() {
		return ModuleNames.PAYROLL_NAME;
	}


}
