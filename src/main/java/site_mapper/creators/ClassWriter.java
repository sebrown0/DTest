/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;

import site_mapper.jaxb.classes.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class ClassWriter {
	private PackageHierarchy ph;	
	private String className;
	private BufferedWriter writer;
	
	
	public ClassWriter(String className, PackageHierarchy ph, BufferedWriter writer) {
		this.className = className;
		this.ph = ph;
		this.writer = writer;
	}


	public void writePackage() throws IOException {
		writer.write("package " + ph.getHierarchyDotNotation() + ";");
		writeNewLines(2);		
	}
	public void openClass(String type) throws IOException {
		if(type != null && type.length() > 0) {
			writer.write("public class " + className + " extends " + type + " {");
		}else {
			writer.write("public class " + className + " {");	
		}		
		writeNewLines(2);		
	}
	public void closeClass() throws IOException {
		writer.write("}");	
	}
	
	private void writeNewLines(int numLines) throws IOException {
		for (int i = 1; i <= numLines; i++) {
			writer.newLine();	
		}
	}
}
