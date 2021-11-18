/**
 * 
 */
package factories;

import object_models.dk_grid.buttons.AddRecord;
import object_models.dk_grid.buttons.DeleteRecord;
import object_models.dk_grid.buttons.DownloadToCsv;
import object_models.dk_grid.buttons.GridButton;
import object_models.dk_grid.buttons.RefreshGrid;
import object_models.dk_grid.buttons.ResetGrid;
import object_models.dk_grid.buttons.SaveChanges;
import object_models.dk_grid.buttons.SaveGrid;
import object_models.dk_grid.buttons.UploadGrid;
import object_models.element.ElementButton;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class GridButtonFactory {
	public static GridButton getButton(ElementButton elmntBtn) {
		GridButton gridButton = null;
		
		switch (elmntBtn.getElementKey()) {
		case "fa fa-fw fa-plus":
			gridButton = new AddRecord(elmntBtn);
			break;
		case "fa fa-fw fa-remove":
			gridButton = new DeleteRecord(elmntBtn);
			break;
		case "fa fa-fw fa-save":
			gridButton = new SaveChanges(elmntBtn);
			break;
		case "fa fa-fw fa-download":
			gridButton = new DownloadToCsv(elmntBtn);
			break;
		case "Upload":
			gridButton = new UploadGrid(elmntBtn);
			break;
		case "fa fa-fw fa-upload":
			gridButton = new UploadGrid(elmntBtn);
			break;
		case "fa fa-fw fa-table":
			gridButton = new SaveGrid(elmntBtn);
			break;
		case "fa fa-fw fa-refresh":
			gridButton = new RefreshGrid(elmntBtn);
			break;
		case "fa fa-fw fa-rotate-left":
			gridButton = new ResetGrid(elmntBtn);
			break;
		default:
			break;
		}
		
		return gridButton;
	}
}
