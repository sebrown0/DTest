/**
 * 
 */
package object_models.dk_grid;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author Steve Brown
 *
 * Sort a column in the grid in a SortDirection.
 */
public class ColumnSorter {
	private WebElement colLabel;
	private int numberOfSortDirs;
	private int sortNumber = 0;
	
	public ColumnSorter(WebElement colLabel) {
		this.colLabel = colLabel;
		this.numberOfSortDirs = SortDirection.values().length;
	}

	/*
	 * Keep sorting (clickAndSortColumn) the column until:
	 *  a) the number of directions to sort is exceeded, or
	 *  b) the required sort direction is found. 
	 */
	public void sortColumn(SortDirection sortDir) {
		if (++sortNumber <= numberOfSortDirs) {
			Optional<String> currentDirRef = Optional.ofNullable(getCurrentSortDirRef());
			currentDirRef.ifPresentOrElse(ref -> {
				if(requiredIsNotCurrent(ref, sortDir.getRef())) {
					clickAndSortColumn(sortDir);
					}}, 
					new Runnable() {					
						@Override
						public void run() {
							clickAndSortColumn(sortDir);
					}
			});	
		}		
	}
	
	private String getCurrentSortDirRef() {
		String dir = null;		
		try {
			WebElement sort = getSortRef();
			if(sort != null) {
				dir = sort.getAttribute("ref");	
			}	
		} catch (Exception e) { 	
		}
		return dir;
	}
	
	private WebElement getSortRef() {
		WebElement sortRef = 
				colLabel.findElement(
						By.cssSelector("span[class^='ag-header-icon ag-header-label-icon ag-sort']:not([class*='ag-hidden'])"));
		return sortRef;
	}
	
	private boolean requiredIsNotCurrent(String current, String required) {
		return current.equals(required) ? false : true;
	}
	
	private void clickAndSortColumn(SortDirection sortDir) {
		colLabel.click();
		sortColumn(sortDir);
	}
}
