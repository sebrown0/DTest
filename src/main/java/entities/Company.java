/**
 * 
 */
package entities;

import java.util.List;

import utils.ListSetter;
import utils.ListTest;

/**
 * @author SteveBrown
 * @version 1.1
 * @since 1.0
 *
 */
public class Company {
	private String name;
	private ListSetter<PayGroup> ls = new ListSetter<>();

	public Company(String name) {
		this.name = name;
	}
	
	public void addPayGroup(PayGroup pg) {
		ls.addValue(pg);		
	}
	public PayGroup getPayGroup(String groupName) {
		ListTest<PayGroup, String> test = (t,v) -> { return t.getPayGroupName().equals(v); };
		return ls.getValue(test, groupName);
	}	
	public void setPayGroups(List<PayGroup> payPeriods) {
		ls.setValues(payPeriods);
	}
	public List<PayGroup> getPayGroups() {
		return ((ListSetter<PayGroup>) ls).getValues();
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
