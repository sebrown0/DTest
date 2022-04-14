/**
 * 
 */
package parameter_resolvers;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.openqa.selenium.WebDriver;

import library.dakar_hr.entities.company.Company;
import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.object_models.modules.payroll.PayrollModuleElements;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Pass company to PayrollModuleElements.
 * @version 1.2
 * 	Get company from config.
 * @since 1.0
 *
 */
public class LoginPageResolverPayroll extends LoginPageResolver {

	@Override
	public Object resolveParameter(ParameterContext pc, ExtensionContext ec) throws ParameterResolutionException {
		ConfigReader reader = (ConfigReader) ec.getStore(Namespace.GLOBAL).get(ConfigParameterResolver.CONFIG_PARAM_ID);		
		WebDriver driver = reader.getDriver();
		LogManager.getLogger(LoginPageResolverPayroll.class).debug("Loging in with default company");
		return 
				new UserLoginPage(
						driver,
						reader.getUri(),
						new PayrollModuleElements(new Company(reader.getCompany())));
	}

}
