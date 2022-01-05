/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.classes.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
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
		componentWriter.getImportNames().forEach(n -> {
			try {
				addImport(n);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		writeNewLine();		
	}
	
	private void addImport(ImportType type) throws IOException {
		writer.write(type.getPath());
		writeNewLine();
	}
	
	public void writeComments() throws IOException {
		writer.write(Comments.getClassComments(elementClass.getSiteMap()));
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
			writer.write("\npublic class " + className + " extends " + type + " {");
		}else {
			writer.write("\npublic class " + className + " {");	
		}		
		writeNewLine();		
	}

	public void closeClass() throws IOException {
		writer.write("\n}");	
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
