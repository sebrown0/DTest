/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.classes.menu_items.JsPanelWithIframe;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ComponentWriterJsPanelWithIFrame implements ComponentWriterVisitor{
	private ClassWriterActions fileOut;
	private JsPanelWithIframe jsPanel;
	
	@Override //ComponentWriter
	public List<String> getImportNames() {
		return Arrays.asList("JsPanelWithIFrame","CoreData");
	}

	@Override //ComponentWriter
	public List<String> getSuperArgs() {
		return Arrays.asList("coreData", "PANEL_TITLE");
	}
	
	@Override //ComponentWriter
	public List<String> getConstructorArgs() {
		return Arrays.asList("CoreData coreData");
	}

	@Override //ComponentWriterVisitor
	public ComponentWriterVisitor setFileOutWriter(ClassWriterActions fileOut) {
		this.fileOut = fileOut;
		return this;
	}

	@Override //ComponentWriterVisitor
	public ComponentWriterVisitor setElementClass(ElementClass elementClass) {
		this.jsPanel = (JsPanelWithIframe) elementClass.getMenuItemType().getJs();
		return this;
	}
	
	@Override //ComponentWriterVisitor
	public void writeComponents() throws IOException {
		writePanelVars();
	}

	private void writePanelVars() throws IOException {
		writeStaticString("PANEL_TITLE", jsPanel.getPanelTitle());
		writeStaticString("MENU_TITLE", jsPanel.getMenuTitle());
		writeStaticString("MENU_PARENT_NAME", jsPanel.getMenuParentName());
		fileOut.writeNewLine();
	}

	private void writeStaticString(String name, String val) throws IOException {
		fileOut.writeLine(String.format("\tpublic static final String %s = \"%s\";", name, val));
	}

}
