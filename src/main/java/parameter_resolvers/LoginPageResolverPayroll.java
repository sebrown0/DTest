/**
 * 
 */
package parameter_resolvers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.openqa.selenium.WebDriver;

import object_models.modules.PayrollModuleLoader;
import object_models.pages.UserLoginPage;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 */
public class LoginPageResolverPayroll extends LoginPageResolver {
	
	@Override
	public Object resolveParameter(ParameterContext pc, ExtensionContext ec) throws ParameterResolutionException {
		ConfigReader reader = (ConfigReader) ec.getStore(Namespace.GLOBAL).get(ConfigParameterResolver.CONFIG_PARAM_ID);
		WebDriver driver = reader.getDriver();
		return new UserLoginPage(driver, new PayrollModuleLoader(driver));
	}

}
