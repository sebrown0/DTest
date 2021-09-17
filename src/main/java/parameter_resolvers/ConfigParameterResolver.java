/**
 * 
 */
package parameter_resolvers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 * Get a default ConfigReader.
 */
public class ConfigParameterResolver implements ParameterResolver {
	public static final String CONFIG_PARAM_ID = ConfigReader.class.getName() + ".PARAM_ID";
	
	@Override
	public boolean supportsParameter(ParameterContext pc, ExtensionContext ec) throws ParameterResolutionException {    
      return pc.getParameter().getType().equals(ConfigReader.class);
	}

	@Override
	public Object resolveParameter(ParameterContext pc, ExtensionContext ec) throws ParameterResolutionException {
		ConfigReader reader = new ConfigReader();
		ec.getStore(Namespace.GLOBAL).put(CONFIG_PARAM_ID, reader);
		return reader;
	}

}
