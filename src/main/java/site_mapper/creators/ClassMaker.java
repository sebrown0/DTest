package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Direct the creation of each required
 * element of the class.
 * 
 */
public class ClassMaker {
	private ElementClass elementClass;
	private PackageHierarchy packageHierarchy;
	private String className;
	private Optional<BufferedWriter> fileOut;
	
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
		this.packageHierarchy = ph;
	}

	public void makeClass() {
		setWriter();
		fileOut.ifPresent(fileWriter -> {
			
			ComponentWriter compWriter = 
					ClassComponentFactory.getComponentWriter(elementClass.getTypeName());
			
			ClassWriter classWriter = 
					new ClassWriter(elementClass, packageHierarchy, fileWriter, compWriter);
			
			try {				
				classWriter.writePackage();
				classWriter.writeImports();
				classWriter.writeComments();
				classWriter.openClass(elementClass.getTypeName());				
				// Write elements specific to the class. 
				classWriter.writeIndividualElements(compWriter);				
				classWriter.closeClass();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			closeFile();
		});
	}
	
	private void setWriter(){
		try {
			fileOut = 				
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
		return 
				packageHierarchy.getRoot() + "/" + 
				packageHierarchy.getHierarchyFwdSlashNotation() + "/" + 
				className + ".java";
	}
	
	private void closeFile() {
		fileOut.ifPresent(w -> {
			try {
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}
