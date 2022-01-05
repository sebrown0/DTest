package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import site_mapper.elements.ElementClass;
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
	private ElementClass elementClass;
	private PackageHierarchy ph;
	private String className;
	private Optional<BufferedWriter> fileOut;
	
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
		this.ph = ph;
	}

	public void makeClass() {
		setWriter();
		fileOut.ifPresent(w -> {
			try {
				ComponentWriter compWriter = ClassComponentFactory.getComponentWriter(elementClass.getTypeName()); 
				ClassWriter classWriter = new ClassWriter(elementClass, ph, w, compWriter);
				
				classWriter.writePackage();
				classWriter.writeImports();
				classWriter.openClass(elementClass.getTypeName());
//				classWriter.createConstructor();				
				
				// Visitor
				classWriter.writeIndividualElements(compWriter);				
				// Visitor
				
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
		return ph.getRoot() + "/" + ph.getHierarchyFwdSlashNotation() + "/" + className + ".java";
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
