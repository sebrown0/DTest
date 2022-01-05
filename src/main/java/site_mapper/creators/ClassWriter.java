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
public class ClassWriter implements ClassWriterActions {
	private PackageHierarchy ph;	
	private String className;
	private BufferedWriter writer;
	private ComponentWriter componentWriter;
	private ElementClass elementClass;
	
	public ClassWriter(ElementClass elementClass, PackageHierarchy ph, BufferedWriter writer, ComponentWriter componentWriter) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
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
		writeNewLine();		
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
	
	public void writeIndividualElements(ComponentWriter compWriter) throws IOException {
		if(compWriter instanceof ComponentWriterVisitor ) {
			((ComponentWriterVisitor) compWriter)
				.setElementClass(elementClass)
				.setFileOutWriter(this)
				.writeComponents();		
		}		
	}
	
	public void openClass(String type) throws IOException {
		if(type != null && type.length() > 0) {
			writer.write("public class " + className + " extends " + type + " {");
		}else {
			writer.write("public class " + className + " {");	
		}		
		writeNewLine();		
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
	@Override //ClassWriterActions
	public void writeNewLines(int numLines) throws IOException {
		for (int i = 1; i <= numLines; i++) {
			writer.newLine();	
		}
	}
	@Override //ClassWriterActions
	public void writeNewLine() throws IOException {
		writer.newLine();
	}
	@Override //ClassWriterActions
	public void addTab() throws IOException {
		writer.write("\t");
	}
//	@Override //ClassWriterActions
//	public BufferedWriter getWriter() {
//		return writer;
//	}

	@Override
	public void writeValue(String value) throws IOException {
		writer.write(value);
	}

	@Override
	public void writeLine(String value) throws IOException {
		writer.write(value);
		writer.newLine();
	}
}
