/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidArgumentException;
import site_mapper.elements.Element;
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
	private String className;
	private JsPanelWithIframe jsPanel;
	private List<Element> elements;
	
	@Override //ComponentWriter
	public List<ImportType> getImportNames() {
		return Arrays.asList(
				new UseImport("java.util.List"),
				new UseImport("org.openqa.selenium.By"),
				new UseImport("control_builder.*"),
				new FindImport("JsPanelWithIFrame"),
				new FindImport("CoreData"));
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
		this.elements = elementClass.getElements();
		this.className = elementClass.getClassName();
		return this;
	}
	
	@Override //ComponentWriterVisitor
	public void writeComponents() throws IOException {
		writePanelVars();
		
//		fileOut.writeConstuctor(Arrays.asList("\t\tbuildMyControls();"));
		writeConstuctor(Arrays.asList("\t\tbuildMyControls();"));
		writeBuildControlsFunction();
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
	
	private void writeConstuctor(List<String> lines) throws IOException {
		String constructor = "public " + className + "(";
		for (String arg : getConstructorArgs()) {
			constructor += arg + ",";
		}		
		constructor = constructor.substring(0, constructor.length()-1) + ") {";
		
		fileOut.addTab();
		fileOut.writeValue(constructor);
		fileOut.writeNewLine();
		fileOut.addTab();
		writeSuperConstructor();
		for (String s : lines) {
			fileOut.writeValue(s);
			fileOut.writeNewLine();	
		}
		fileOut.addTab();
		fileOut.writeValue("}");
		fileOut.writeNewLine();
	}
	public void writeSuperConstructor() throws IOException {
		List<String> args = getSuperArgs();
		if(args.size()>0) {
			String constructor = "super(";
			for (String arg : args) {
				constructor += arg + ",";
			}
			if(constructor.endsWith(",")) {
				constructor = constructor.substring(0, constructor.length()-1);
			}
			constructor += ");";
			fileOut.addTab();
			fileOut.writeValue(constructor);
			fileOut.writeNewLine();
		}
		
	}
	
	private void writeBuildControlsFunction() throws IOException {		
		String func;
		if(elements != null) {
			List<ControlDataValues> values = new ArrayList<>();
					
			elements.forEach(e -> {
				values.add(new ControlDataValues(e.getName(), e.getType(), e.getLocator(), e.getBy()));
			});	
			
			ControlDataStringFactory fact = new ControlDataStringFactory(values);
			try {
				func = fact.getFunctionBuildMyControls();
				fileOut.writeNewLine();
				fileOut.writeValue(func);
				System.out.println("FUNC\n" + func); // TODO - remove or log 	
			} catch (InvalidArgumentException e1) {
				// TODO Auto-generated catch block
				// ** TODO - Add to sitemapper log **
				System.out.println("ComponentWriterJsPanelWithIFrame.writeBuildControlsFunction -> ** TODO - Add to sitemapper log **"); // TODO - remove or log 	
				e1.printStackTrace();
			}
			
		}		
	}
	/* 
Element [type=button, name=save, by=css, locator=button[name='SAVE'], text=Save, fafa=fa fa-save, response=null]
Element [type=button, name=search, by=css, locator=button[name='QBF1'], text=Search, fafa=fa fa-search, response=null]
Element [type=text_out, name=code, by=css, locator=input[id='FORM_ID'], text=EMP_CODE, fafa=null, response=null]
	 */
}
