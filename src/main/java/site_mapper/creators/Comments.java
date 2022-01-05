/**
 * 
 */
package site_mapper.creators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import site_mapper.jaxb.classes.pom.SiteMap;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Comments {
	public static String getPackageComments(SiteMap siteMap) {
		return 
				"/**\n* Generated Package." + 
				"\n* ------------------" +
				getFields(siteMap) + 
				"\n*/\n";
	}
	
	public static String getClassComments(SiteMap siteMap) {
		return 
				"/**\n* Generated Class." + 
				"\n* ----------------" +
				getFields(siteMap) + 
				"\n*/\n";
	}
	
	private static String getFields(SiteMap siteMap) {
		return 
				"\n* Source:  " + siteMap.getXmlSource() +
				"\n* Author:  " +	siteMap.getAuthor() + 
				"\n* Version: " + siteMap.getVersion() +
				"\n* Created: " + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
	}
}
