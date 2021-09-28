/**
 * 
 */
package object_models.dk_grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author SteveBrown
 *
 */
public class DkGridContentReader {
	private WebDriver driver;
	private WebElement gridElement;
	private WebElement contentElement;	
	private Logger logger = LogManager.getLogger();	
	private DkGridContent gridContent;
	
	private String[] containerNames = new String[3];	
	
	
	public DkGridContentReader(WebDriver driver, DkGridContent gridContent) {
		this.driver = driver;
		this.gridContent = gridContent;
		setContainerNames();
		setGridElement();
		setContentElement();
	}
	
	private void setContainerNames() {
		containerNames[0] = "ag-pinned-left-cols-container";
		containerNames[1] = "ag-center-cols-container";
		containerNames[2] = "ag-pinned-right-cols-container ag-hidden"; 
	}
	
	private void setGridElement() {
		gridElement = driver.findElement(By.id("dkrGrid"));
	}
	
	private void setContentElement() {
		contentElement = gridElement.findElement(By.cssSelector("div[class='ag-body-viewport ag-layout-normal ag-row-animation']"));
	}
	
	public void read() {
		List<WebElement> rowElements = contentElement.findElements(By.cssSelector("div[role='row']"));
		loopContainers();
//		for (WebElement e : rowElements) {
//			System.out.println("->" + e.getAttribute("row-index"));
//		}
	}
	
	private void loopContainers(){
		WebElement container = null;		
		for (String className : containerNames) {			
			container = contentElement.findElement(By.cssSelector("div[class='" + className + "']"));
			System.out.println("->" + container.getAttribute("ref"));
			getRowsInContainer(container);
		}
	}
	
	private void getRowsInContainer(WebElement container) {
//		container.findElements(By.cssSelector("div[role='row']")).forEach(r -> System.out.println("r->" + r.getText()));
		container.findElements(By.cssSelector("div[role='row']")).forEach(r -> getColsInRow(r));
	}
	
	private void getColsInRow(WebElement row) {
		row.findElements(By.cssSelector("div[role='gridcell']")).forEach(c -> System.out.println(c.getAttribute("col-id").toLowerCase()));
	}
}
/*
#dkrGrid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-animation > div.ag-pinned-left-cols-container
ROW

<div 
		role="row" 
		row-index="3" 
		aria-rowindex="6" 
		row-id="0" 
		comp-id="86" 
		class="ag-row ag-row-no-focus ag-row-level-0 ag-row-position-absolute ag-row-odd" 
		style="height: 30px; transform: translateY(90px);"><div tabindex="-1" unselectable="on" role="presentation" comp-id="95" col-id="_selection" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-range-left" style="width: 40px; left: 0px;  "><div ref="eCellWrapper" class="ag-cell-wrapper" role="presentation">
                <div class="ag-selection-checkbox">
                <!--AG-CHECKBOX--><div role="presentation" ref="eCheckbox" class="ag-labeled ag-label-align-right ag-checkbox ag-input-field">
            <label ref="eLabel" class="ag-input-field-label ag-label ag-hidden ag-checkbox-label" for="ag-input-id-231"></label>
            <div ref="eWrapper" class="ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper" role="presentation">
                <input ref="eInput" class="ag-input-field-input ag-checkbox-input" type="checkbox" id="ag-input-id-231" aria-label="Toggle Row Selection">
            </div>
        </div>
            </div><span ref="eCellValue" role="gridcell" aria-colindex="1" class="ag-cell-value" unselectable="on"></span></div></div><div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="2" comp-id="96" col-id="@ID" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height rag-ro-cell ag-cell-value" style="width: 150px; left: 40px;  ">S01880</div><div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="3" comp-id="97" col-id="ALL_NAME" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-last-left-pinned rag-ro-cell ag-cell-value" style="width: 300px; left: 190px;  ">Mr KENNETH Bugeja </div></div>



--------------------------------------------------------------------------------------------------------
CELL
<div 	tabindex="-1" 
			unselectable="on" 
			role="gridcell" 
			aria-colindex="2" 
			comp-id="96" 
			col-id="@ID" 
			class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height rag-ro-cell ag-cell-value" 
			style="width: 150px; left: 40px;  ">
			
			S01880
</div>




<div role="row" row-index="3" aria-rowindex="6" row-id="0" comp-id="78" class="ag-row ag-row-no-focus ag-row-level-0 ag-row-position-absolute ag-row-odd" style="height: 30px; transform: translateY(90px);"><div tabindex="-1" unselectable="on" role="presentation" comp-id="86" col-id="_selection" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-range-left" style="width: 40px; left: 0px;  "><div ref="eCellWrapper" class="ag-cell-wrapper" role="presentation">
<div class="ag-selection-checkbox">
<!--AG-CHECKBOX--><div role="presentation" ref="eCheckbox" class="ag-labeled ag-label-align-right ag-checkbox ag-input-field">
<label ref="eLabel" class="ag-input-field-label ag-label ag-hidden ag-checkbox-label" for="ag-input-id-211"></label>
<div ref="eWrapper" class="ag-wrapper ag-input-wrapper ag-checkbox-input-wrapper" role="presentation">
<input ref="eInput" class="ag-input-field-input ag-checkbox-input" type="checkbox" id="ag-input-id-211" aria-label="Toggle Row Selection">
</div>
</div>
</div><span ref="eCellValue" role="gridcell" aria-colindex="1" class="ag-cell-value" unselectable="on"></span>
</div></div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="2" comp-id="87" col-id="@ID" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height rag-ro-cell ag-cell-value" style="width: 150px; left: 40px;  ">S01880</div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="3" comp-id="88" col-id="ALL_NAME" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-last-left-pinned rag-ro-cell ag-cell-value" style="width: 300px; left: 190px;  ">Mr KENNETH Bugeja </div></div>


ag-center-cols-container
<div role="row" row-index="3" aria-rowindex="6" row-id="0" comp-id="78" class="ag-row ag-row-no-focus ag-row-level-0 ag-row-position-absolute ag-row-odd" style="height: 30px; transform: translateY(90px);">
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="4" comp-id="79" col-id="IDENTITY_CARD_NO" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 0px;  ">382698M</div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="5" comp-id="80" col-id="TITLE" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 170px;  "><span>Mr</span></div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="6" comp-id="81" col-id="SURNAME" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 340px;  ">Bugeja</div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="7" comp-id="82" col-id="NAME" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 510px;  ">KENNETH</div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="8" comp-id="83" col-id="TOWN" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 680px;  "><span>San Gwann</span></div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="9" comp-id="84" col-id="POST_CODE" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 170px; left: 850px;  "></div>
<div tabindex="-1" unselectable="on" role="gridcell" aria-colindex="10" comp-id="85" col-id="COUNTRY" class="ag-cell ag-cell-not-inline-editing ag-cell-auto-height ag-cell-value" style="width: 150px; left: 1020px;  "><span>MALTA</span></div></div>
*/