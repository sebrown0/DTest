/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.classes.pom.PackageHierarchy;
import utils.FileFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class ClassWriter {
	private PackageHierarchy ph;	
	private String className;
	private BufferedWriter writer;
	private ComponentWriter componentWriter;
	
	public ClassWriter(String className, PackageHierarchy ph, BufferedWriter writer, ComponentWriter componentWriter) {
		this.className = className;
		this.ph = ph;
		this.writer = writer;
		this.componentWriter = componentWriter;
	}

	public void writePackage() throws IOException {
		writer.write("package " + ph.getHierarchyDotNotation() + ";");
		writeNewLines(2);		
	}
	public void writeImports() throws IOException {
		componentWriter.getImportNames().forEach(n -> addImport(n));
		writeNewLines(2);		
	}
	
	private void addImport(String importName) {
		
		getImportPath(importName).ifPresent(p -> {
			try {
				writer.write("import " + p + ";");
				writeNewLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		});
	}
	private Optional<String> getImportPath(String importName){
		return 
		  Optional.ofNullable(
			  FileFinder
					.findPathWithoutRootAndExtension("./src/main/java", importName + ".java")
					.replaceAll("\\\\", "."));
	}
	
	public void writePanelVars(ElementClass elementClass) {
		System.out.println("->" + elementClass.getMenuItemType().getMenuParentName()); // TODO - remove or log 	
		System.out.println("->" + elementClass.getMenuItemType().getMenuTitle()); // TODO - remove or log
		System.out.println("->" + elementClass.getMenuItemType().getPanelTitle()); // TODO - remove or log
	}
	
	public void openClass(String type) throws IOException {
		if(type != null && type.length() > 0) {
			writer.write("public class " + className + " extends " + type + " {");
		}else {
			writer.write("public class " + className + " {");	
		}		
		writeNewLines(2);		
	}
	public void createConstructor() throws IOException {
		String constructor = "public " + className + "(";
		for (String arg : componentWriter.getConstructorArgs()) {
			constructor += arg + ",";
		}
		
		if(constructor.endsWith(",")) {
			constructor = constructor.substring(0, constructor.length()-1);
		}
		
		constructor += ") {";
		addTab();
		writer.write(constructor);
		writeNewLine();
		addTab();
		writeSuperConstructor();
		addTab();
		writer.write("}");
		writeNewLine();
	}
	public void writeSuperConstructor() throws IOException {
		List<String> args = componentWriter.getSuperArgs();
		if(args.size()>0) {
			String constructor = "super(";
			for (String arg : args) {
				constructor += arg + ",";
			}
			if(constructor.endsWith(",")) {
				constructor = constructor.substring(0, constructor.length()-1);
			}
			constructor += ");";
			addTab();
			writer.write(constructor);
			writeNewLine();
		}
		
	}
	public void closeClass() throws IOException {
		writer.write("}");	
	}
	
	private void writeNewLines(int numLines) throws IOException {
		for (int i = 1; i <= numLines; i++) {
			writer.newLine();	
		}
	}
	private void writeNewLine() throws IOException {
		writer.newLine();
	}
	private void addTab() throws IOException {
		writer.write("\t");
	}
}
