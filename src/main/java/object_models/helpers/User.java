/**
 * 
 */
package object_models.helpers;

/**
 * @author SteveBrown
 *
 */
public class User {
	private String name;
	private String pswd;
	
	public User(String name, String pswd) {
		super();
		this.name = name;
		this.pswd = pswd;
	}

	public User() {	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
}
