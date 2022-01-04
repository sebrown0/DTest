/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import site_mapper.jaxb.classes.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Create specified package(s), starting at the given root.
 * If the package already exists it will not be overwritten.
 * 
 */
public class PackageMaker {
	private static String filePath;
	private static String packagePath;
	
	public static void makeWithPackageInfo(PackageHierarchy ph) {
		filePath = ph.getRoot() + "/" + ph.getHierarchyFwdSlashNotation();
		packagePath = ph.getHierarchyDotNotation();
		
		makePackages();
		createPackageInfo();
	}
	
	public static void makeWithPackageInfo(String root, String packageName) {
		filePath = root + "/" + packageName;
		packagePath = packageName;
		
		makePackages();
		createPackageInfo();
	}
	
	public static void makeWithPackageInfo(String root, String parentPackage, String packageName) {
		filePath = root + "/" + parentPackage + "/" + packageName;
		packagePath = parentPackage + "." + packageName;
		
		makePackages();
		createPackageInfo();
	}
	
	private static void makePackages() {
		new File(filePath).mkdirs();
	}
	
	private static void createPackageInfo() {		
		try (Writer writer = 
				new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath + "/package-info.java"), StandardCharsets.UTF_8))) {
	    writer.write("package " + packagePath + ";");
		} 
		catch (IOException ex) {
		    // TODO - Handle me
		}  
	}
}