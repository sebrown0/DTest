/**
 * 
 */
package parameter_resolvers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import context_manager.ContextManager;
import object_models.pages.UserLoginPage;

/**
 * @author Steve Brown
 *
 */
public abstract class LoginPageResolver implements ParameterResolver {	
	protected ContextManager contextManager;
	
	public LoginPageResolver(ContextManager contextManager) {
		this.contextManager = contextManager;
	}

	@Override
	public boolean supportsParameter(ParameterContext pc, ExtensionContext ec) throws ParameterResolutionException {    
      return pc.getParameter().getType().equals(UserLoginPage.class);
	}
}
