/**
 * 
 */
package test_data;

import java.util.stream.Stream;

import entities.User;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add userSteveB().
 * @since 1.0
 *
 */
public class UserProvider {

	public static User userPortal() {
		return new User("portal2", "123");
	}
	
	public static User userSteveB() {
		return new User("steve", "1234");
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
