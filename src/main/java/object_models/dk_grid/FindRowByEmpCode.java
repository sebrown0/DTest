package object_models.dk_grid;

/**
 * @author Steve Brown
 * 
 */
public class FindRowByEmpCode extends FindRow {
	private static final String KEY = ColumnName.EMP_CODE.value;
	
	public FindRowByEmpCode() {
		super(KEY);		
	}
	
}
