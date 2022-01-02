package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import site_mapper.jaxb.classes.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * 
 */
public class ClassMaker {
	private PackageHierarchy ph;
	private String className;
	private Optional<BufferedWriter> writer;
	
	public ClassMaker(String className, PackageHierarchy ph) {
		this.className = className;
		this.ph = ph;
	}

	/*
	 * CREATEING THE CLASS WITH PACKAGE AND CLASS NAME.
	 * HAVE TO ADD 
	 * 	ELEMENTS
	 * 	CONSTRUCTOR
	 * 	INTERFACES ETC, ETC
	 */
	public void makeClass() {
		setWriter();
		writer.ifPresent(w -> {
			try {
				w.write("package " + ph.getHierarchyDotNotation() + ";");
				w.newLine();
				w.newLine();
				w.write("public class " + className + " {");
				w.newLine();
				w.newLine();
				w.write("}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			closeFile();
		});
	}
	
	private void setWriter(){
		try {
			writer = 				
					Optional.ofNullable(new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(
										getFilePath()),	StandardCharsets.UTF_8)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getFilePath() {
		return ph.getRoot() + "/" + ph.getHierarchyFwdSlashNotation() + "/" + className + ".java";
	}
	
	private void closeFile() {
		writer.ifPresent(w -> {
			try {
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
