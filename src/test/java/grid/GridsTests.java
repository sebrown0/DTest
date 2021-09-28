package grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import object_models.dk_grid.Cell;
import object_models.dk_grid.Cell.ColumnName;
import object_models.dk_grid.FindRowByEmpCode;
import object_models.dk_grid.GridData;
import object_models.dk_grid.Row;
import object_models.helpers.TestFail;

class GridsTests {
//	private static GridData<FindRowById, FindColumnById> gridDataById = new GridData<>();;
	private static GridData gridDataById = new GridData();
	
	@BeforeAll
	public static void setup() {
		Cell cell1 = new Cell("col_id_1", "EMP_CODE_DMS1", "", "");
		Cell cell2 = new Cell("col_id_2", "Duck McScrooge", "", "");
		Cell cell3 = new Cell("col_id_3", "21/01/2000", "", "");
		Cell cell4 = new Cell("col_id_4", "Malta", "", "");
		
		Cell cell5 = new Cell("col_id_5", "EMP_CODE_DMS2", "", "");
		
		Row row1 =new Row(new FindRowByEmpCode());
		row1.addCell(ColumnName.EMP_CODE, cell1);
		row1.addCell(ColumnName.ALL_NAME, cell2);		
		
		Row row2 =new Row(new FindRowByEmpCode());
		row2.addCell(ColumnName.EMP_CODE, cell5);
		row2.addCell(ColumnName.DOB, cell3);
		row2.addCell(ColumnName.COUNTRY, cell4);
		
		gridDataById.addRow("leftContainer", row1);
		gridDataById.addRow("leftContainer", row2);		
		gridDataById.addRow("centreContainer", row2);
		
		
	}

	@Test
	void getRowByEmployeeCode_Using_Cell_1() {
		Optional<Row> row = gridDataById.getRow("leftContainer", "EMP_CODE_DMS1");
		row.ifPresentOrElse(r ->
				assertEquals("Duck McScrooge", r.getCell(ColumnName.ALL_NAME).getValue().get()), 
				new TestFail("Row for value is missing"));
	}
	
	@Test
	void getRowByEmployeeCode_Using_Cell_2() {		
		Optional<Row> row = gridDataById.getRow("leftContainer", "EMP_CODE_DMS2");
		row.ifPresentOrElse(r ->
				assertEquals("Malta", r.getCell(ColumnName.COUNTRY).getValue().get()), 
				new TestFail("Row for value is missing"));
	}
	
//	@Test
//	void getValueFromRow2_Col4() {
//		Optional<Row<?>> row = gridDataById.getRow("centreContainer", "id2");
//		Column<?> col = row.get().getColumn("col4");
//		assertEquals(col.getValue(), "value 4");
//	}

}
