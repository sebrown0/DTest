package grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import object_models.dk_grid.Cell;
import object_models.dk_grid.ColumnName;
import object_models.dk_grid.FindRowByEmpCode;
import object_models.dk_grid.GridData;
import object_models.dk_grid.Row;
import object_models.helpers.TestFail;

class GridsTests {
	private static GridData<FindRowByEmpCode> gridData = new GridData<>();;
	
	@BeforeAll
	public static void setup() {
		Cell cell1 = new Cell("col_id_1", "EMP_CODE_DMS1", "", "");
		Cell cell2 = new Cell("col_id_2", "Duck McScrooge", "", "");
		Cell cell3 = new Cell("col_id_3", "21/01/2000", "", "");
		Cell cell4 = new Cell("col_id_4", "Malta", "", "");		
		Cell cell5 = new Cell("col_id_5", "EMP_CODE_DMS2", "", "");
		
		Row<FindRowByEmpCode> row1 =new Row<>(new FindRowByEmpCode());
		row1.addCell(ColumnName.EMP_CODE.value, cell1);
		row1.addCell(ColumnName.ALL_NAME.value, cell2);		
		
		Row<FindRowByEmpCode> row2 =new Row<>(new FindRowByEmpCode());
		row2.addCell(ColumnName.EMP_CODE.value, cell5);
		row2.addCell(ColumnName.DOB.value, cell3);
		row2.addCell(ColumnName.COUNTRY.value, cell4);
		
		gridData.addRow("leftContainer", row1);
		gridData.addRow("leftContainer", row2);		
		gridData.addRow("centreContainer", row2);		
	}

	@Test
	void ffffffffffffffff() {
//		String name = Element.H.name();
//		System.out.println("->" + name);
//		System.out.println("->" + Element.valueOf(Element.class, name));
		for (ColumnName e : ColumnName.values()) {
			System.out.println("->" + e.name() + " ->" + e.value);
		}
	}
	
	@Test
	void getRowByEmployeeCode_Using_Cell_1() {
		Optional<Row<FindRowByEmpCode>> row = gridData.getRow("leftContainer", "EMP_CODE_DMS1");
		row.ifPresentOrElse(r ->
				assertEquals("Duck McScrooge", r.getCell(ColumnName.ALL_NAME.value).getValue().get()), 
				new TestFail("Row for value is missing"));
	}
	
	@Test
	void getRowByEmployeeCode_Using_Cell_2() {		
		Optional<Row<FindRowByEmpCode>> row = gridData.getRow("leftContainer", "EMP_CODE_DMS2");
		row.ifPresentOrElse(r ->
				assertEquals("Malta", r.getCell(ColumnName.COUNTRY.value).getValue().get()), 
				new TestFail("Row for value is missing"));
	}
	
}
