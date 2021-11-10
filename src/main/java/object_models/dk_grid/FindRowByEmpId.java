package object_models.dk_grid;

/**
 * @author Steve Brown
 * 
 */
public class FindRowByEmpId extends FindRow {
	private static final String KEY = ColumnName.EMP_ID.value;
	
	public FindRowByEmpId() {
		super(KEY);		
	}
}
