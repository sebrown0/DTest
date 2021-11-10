package object_models.dk_grid;

public class FindRowByEmpAllName extends FindRow {	
	private static final String KEY = ColumnName.ALL_NAME.value;
	
	protected FindRowByEmpAllName() {
		super(KEY);		
	}
}
