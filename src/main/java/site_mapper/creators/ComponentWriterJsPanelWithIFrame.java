/**
 * 
 */
package site_mapper.creators;

import java.util.Arrays;
import java.util.List;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.classes.JsPanelMenuItemType;
import site_mapper.jaxb.classes.MenuItemType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ComponentWriterJsPanelWithIFrame implements ComponentWriterVisitor{
	private ClassWriterActions fileOut;
	
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
	public void writeComponents(ClassWriterActions fileOut, ElementClass elementClass) {
		this.fileOut = fileOut;
		MenuItemType t = elementClass.getMenuItemType();		
		JsPanelMenuItemType js = t.getJs();
		
		System.out.println("<->" + js.getMenuParentName()); // TODO - remove or log 	
		System.out.println("<->" + js.getMenuTitle()); // TODO - remove or log
		System.out.println("<->" + js.getPanelTitle()); // TODO - remove or log
	}

	private void writePanelVars() {
		
	}

}
