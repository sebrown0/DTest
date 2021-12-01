/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getValue for ListTestPredicate. 
 * @since 1.0
 * @param T the objects in the list.
 * 
 * Generic list of objects.
 */
public class ListSetter <T extends Object> {
	private List<T> values;
	
	public void addValue(T pg) {
		try {
			if(values == null) {
				values = new ArrayList<>();
				values.add(pg);
			}else if(values.contains(pg)) {
				LogManager.getLogger().debug("List already has value");
				//TODO - remove and add new?
			}else {
				values.add(pg);
			}			
		} catch (Exception e) {
			LogManager.getLogger().error("Error adding value to list");
		}		
	}
	
	public <V extends Object> T getValue(ListTestFind<T,V> listTest, V v) {
		T res = null;
		for (T t : values) {
			if(listTest.test(t,v)) {
				res = t;
				break;
			}
		}		
		return res;
	}	
	
	public T getValue(ListTestPredicate<T> listTest) {
		T res = null;
		for (T t : values) {
			if(listTest.test(t) == true) {
				res = t;
				break;
			}
		}		
		return res;
	}	
	
	public List<T> getValues() {
		return values;
	}

	public void setValues(List<T> payPeriods) {
		this.values = payPeriods;
	}
}
