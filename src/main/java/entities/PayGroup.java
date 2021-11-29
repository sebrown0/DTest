/**
 * 
 */
package entities;

import java.util.List;

import utils.ListSetter;
import utils.ListTest;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class PayGroup {
	private String payGroupName;
	private ListSetter<PayPeriod> ls = new ListSetter<>();
	
	public PayGroup(String payGroupName) {
		this.payGroupName = payGroupName;		
	}
	
	public PayGroup(String payGroupName, PayPeriod payPeriod) {
		this.payGroupName = payGroupName;
		addPayPeriod(payPeriod);
	}
	
	public void addPayPeriod(PayPeriod pg) {
		ls.addValue(pg);		
	}
	public PayPeriod getPayPeriod(int periodNum) {
		ListTest<PayPeriod, Integer> test = (t,v) -> { return t.getCurrentPeriodNum() == v; };
		return ls.getValue(test, periodNum);
	}	
	public void setPayPeriods(List<PayPeriod> payPeriods) {
		ls.setValues(payPeriods);
	}
	public List<PayPeriod> getPayPeriods() {
		return ((ListSetter<PayPeriod>) ls).getValues();
	}	
	
	public String getPayGroupName() {
		return payGroupName;
	}
}
