/**
 * 
 */
package entities.company;

import java.util.List;

import entities.payroll.PayGroup;
import utils.ListSetter;
import utils.ListTestFind;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add hashCode().
 * @since 1.0
 *
 */
public class Company {
	private String name;
	private ListSetter<PayGroup> payGroups = new ListSetter<>();

	public Company(String name) {
		this.name = name;
	}
	public Company(String name, PayGroup payGroup) {
		this.name = name;
		this.addPayGroup(payGroup);
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
	public int hashCode() {
		int result = 1;
		final int prime = 31;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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
