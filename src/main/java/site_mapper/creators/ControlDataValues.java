/**
 * 
 */
package site_mapper.creators;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for the values required by ControlDataStringFactory.
 */
public class ControlDataValues {
	private String controlName;
	private String controlTypeName;
	private String byValue;
	private String byType;
	
	public ControlDataValues(String controlName, String controlTypeName, String byValue, String byType) {
		this.controlName = controlName;
		this.controlTypeName = controlTypeName;
		this.byValue = byValue;
		this.byType = byType;
	}
	
	public String getControlName() {
		return controlName;
	}
	public String getControlTypeName() {
		return controlTypeName;
	}
	public String getByValue() {
		return byValue;
	}
	public String getByType() {
		return byType;
	}
	
}
