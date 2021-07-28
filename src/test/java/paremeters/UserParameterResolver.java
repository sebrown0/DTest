package paremeters;

import java.util.Random;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import object_models.helpers.User;

public class UserParameterResolver implements ParameterResolver{
	private int idx;
//	@Retention(RetentionPolicy.RUNTIME)
//	@Target(ElementType.PARAMETER)
//	public @interface UserIndex{	}
	
	public static final User[] VALID_USERS = {
			new User("steve","1234"),
			new User("portal","123")
	};
	
	public void setUserIndex(int idx) {
		this.idx = idx;
	}
	
	public User getUser() {
		User ret = null;
		if (idx >= 0 && idx <= VALID_USERS.length) {
			ret = VALID_USERS[idx];			
		}
		return ret;
	}
	
	public User getUser(int index) {
		User ret = null;
		if (index >= 0 && index <= VALID_USERS.length) {
			ret = VALID_USERS[index];			
		}
		return ret;
	}
	
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		boolean ret = false;		
		if(parameterContext.getParameter().getType() == User.class) {
			ret = true;
		}
		return ret;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		User ret = null;
		if (supportsParameter(parameterContext, extensionContext)) {
			ret = VALID_USERS[new Random().nextInt(VALID_USERS.length)];
		}
		return ret;
	}

}
