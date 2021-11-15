package grid;

import object_models.dk_grid.FindRowByEmpId;
import object_models.dk_grid.GridDataRows;

class GridsTests {
	@SuppressWarnings("unused")
	private static GridDataRows<FindRowByEmpId> gridData = new GridDataRows<>();;
	
//	@BeforeAll
//	public static void setup() {
//		Cell cell1 = new Cell("col_id_1", "EMP_CODE_DMS1", "", "", null);
//		Cell cell2 = new Cell("col_id_2", "Duck McScrooge", "", "", null);
//		Cell cell3 = new Cell("col_id_3", "21/01/2000", "", "", null);
//		Cell cell4 = new Cell("col_id_4", "Malta", "", "", null);		
//		Cell cell5 = new Cell("col_id_5", "EMP_CODE_DMS2", "", "", null);
//		
//		Row<FindRowByEmpId> row1 =new Row<>(new FindRowByEmpId());
//		row1.addCell(ColumnName.EMP_ID.value, cell1);
//		row1.addCell(ColumnName.ALL_NAME.value, cell2);		
//		
//		Row<FindRowByEmpId> row2 =new Row<>(new FindRowByEmpId());
//		row2.addCell(ColumnName.EMP_ID.value, cell5);
//		row2.addCell(ColumnName.DOB.value, cell3);
//		row2.addCell(ColumnName.COUNTRY.value, cell4);
//		
//		gridData.addRow("leftContainer", row1);
//		gridData.addRow("leftContainer", row2);		
//		gridData.addRow("centreContainer", row2);		
//	}
//	
//	@Test
//	void getRowByEmployeeCode_Using_Cell_1() {
//		Optional<Row<?>> row = gridData.getRow("leftContainer", "EMP_CODE_DMS1");
//		row.ifPresentOrElse(r ->
//				assertEquals("Duck McScrooge", r.getCell(ColumnName.ALL_NAME.value).getValue().get()), 
//				new TestFail("Row for value is missing"));
//	}
//	
//	@Test
//	void getRowByEmployeeCode_Using_Cell_2() {		
//		Optional<Row<?>> row = gridData.getRow("leftContainer", "EMP_CODE_DMS2");
//		row.ifPresentOrElse(r ->
//				assertEquals("Malta", r.getCell(ColumnName.COUNTRY.value).getValue().get()), 
//				new TestFail("Row for value is missing"));
//	}
	
}
