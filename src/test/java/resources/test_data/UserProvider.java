/**
 * 
 */
package resources.test_data;

import java.util.stream.Stream;

import object_models.helpers.User;

/**
 * @author Steve Brown
 *
 */
public class UserProvider {

	public static User userPortal() {
		return new User("portal2", "123");
	}
	
	public static Stream<User> validPortalUser(){
		return Stream.of(new User("portal2", "123"));
	}
	
	public static Stream<User> validUser(){
		return Stream.of(new User("steve", "1234"));
	}
	
	static Stream<User> invalidUser(){
		return Stream.of(new User("batman", "0000"));
	}
}
