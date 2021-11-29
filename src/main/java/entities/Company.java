/**
 * 
 */
package entities;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
public class Company {
	private String name;
		
	public Company(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

	  if (other.getClass() != this.getClass()) {
	      return false;
	  }
  
	  final Company otherCo = (Company) other;	  
		return otherCo.getName().equals(name);
	}
	
}
