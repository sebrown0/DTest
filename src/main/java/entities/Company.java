/**
 * 
 */
package entities;

import java.util.List;

import utils.ListSetter;
import utils.ListTestFind;

/**
 * @author SteveBrown
 * @version 1.1
 * @since 1.0
 *
 */
public class Company {
	private String name;
	private ListSetter<PayGroup> payGroups = new ListSetter<>();

	public Company(String name) {
		this.name = name;
	}
	
	public void addPayGroup(PayGroup pg) {
		payGroups.addValue(pg);		
	}
	public PayGroup getPayGroup(String groupName) {
		ListTestFind<PayGroup, String> test = (t,v) -> { return t.getPayGroupName().equals(v); };
		return payGroups.getValue(test, groupName);
	}	
	public void setPayGroups(List<PayGroup> payPeriods) {
		payGroups.setValues(payPeriods);
	}
	public List<PayGroup> getPayGroups() {
		return ((ListSetter<PayGroup>) payGroups).getValues();
	}
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
    if (other == null || other.getClass() != this.getClass()) {
	      return false;
	  }
  
	  Company otherCo = (Company) other;	  
		return otherCo.getName().equals(name);
	}
	
}
