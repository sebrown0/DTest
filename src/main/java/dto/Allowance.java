/**
 * 
 */
package dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Steve Brown
 *
 */
@Getter @Setter @ToString
@SuppressWarnings("unused")
public class Allowance {	
	private String allowanceName;
	private BigDecimal allowanceAmount;
	
	public Allowance(String allowanceName, BigDecimal allowanceAmount) {
		this.allowanceName = allowanceName;
		this.allowanceAmount = allowanceAmount;
	}	
}
