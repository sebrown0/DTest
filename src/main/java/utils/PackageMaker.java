/**
 * 
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PackageMaker {
	private static String filePath;
	
	public static void makeWithPackageInfo(String root, String packageName) {
		filePath = root + "/" + packageName;
		makePackages();
		createPackageInfo(packageName);
	}
	
	public static void makeWithPackageInfo(String root, String parentPackage, String packageName) {
		filePath = root + "/" + parentPackage + "/" + packageName;
		
		makePackages();
		createPackageInfo(parentPackage + "." + packageName);
	}
	
	private static void makePackages() {
		new File(filePath).mkdirs();
	}
	
	private static void createPackageInfo(String packageName) {
		String packageFilePath = filePath + "/package-info.java";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(packageFilePath), StandardCharsets.UTF_8))) {
	    writer.write("package " + packageName + ";");
		} 
		catch (IOException ex) {
		    // TODO - Handle me
		}  
	}
}
