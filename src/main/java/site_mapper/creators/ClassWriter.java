/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Optional;

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
			addImport(type);
			writer.write("public class " + className + " extends " + type + " {");
		}else {
			writer.write("public class " + className + " {");	
		}		
		writeNewLines(2);		
	}
	private void addImport(String importName) {
//		Optional<String> importPath = 
//				Optional.ofNullable(
//						FileFinder
//							.findPathWithoutRootAndExtension("./src/main/java", importName + ".java")
//							.replaceAll("\\\\", "."));
		
		getImportPath(importName).ifPresent(p -> {
			try {
				writer.write("import " + p + ";");
				writeNewLines(2);		
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
	
	public void closeClass() throws IOException {
		writer.write("}");	
	}
	
	private void writeNewLines(int numLines) throws IOException {
		for (int i = 1; i <= numLines; i++) {
			writer.newLine();	
		}
	}
}
