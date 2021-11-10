/**
 * 
 */
package object_models.dk_grid;

/**
 * @author Steve Brown
 *
 */
public enum ColumnName {	
	COL_ID("COL_ID"),
	EMP_ID("@id"),
	EMP_CODE("employee_code"),
	ALL_NAME("all_name"),
	IDENTITY_CARD_NO("identity_card_no"),
	TITLE("title"),
	SURNAME("surname"),
	NAME("name"),
	TOWN("town"),
	POST_CODE("post_code"),
	COUNTRY("country"),
	AGE("age"),
	GENDER("sex"),
	DOB("date_of_birth");

  public final String value;

  private ColumnName(String value) {
      this.value = value;
  }
}
