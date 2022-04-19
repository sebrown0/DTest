/**
 * 
 */
package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ZZZ_FileFinder {
	private static Optional<String> filePath;
	private static String result;
	
	public static String findPathWithoutRootAndExtension(final String ROOT, final String fileName) {
		result = findPathWithoutRoot(ROOT, fileName);
		if(result.length() > 0) {
			int pos = result.indexOf(".");
			result = result.substring(0, pos);
		}
		return result;
	}
	
	public static String findPathWithoutRoot(final String ROOT, final String fileName) {
		result = "";
		findFilePath(ROOT, fileName).ifPresent(fp ->{
			result = fp.substring(ROOT.length() + 1);
		});		
		return result;
	}
	
	public static String findPathWithRoot(final String ROOT, final String fileName) {
		result = "";
		findFilePath(ROOT, fileName).ifPresent(fp ->{
			result = fp;
		});		
		return result;
	}
	
	private static Optional<String> findFilePath(final String ROOT, final String fileName){
		filePath = null;
		try (Stream<Path> walkStream = Files.walk(Paths.get(ROOT))) {
	    walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
	      if (f.toString().endsWith("\\" + fileName)) {
	      	filePath = Optional.ofNullable(f.toString());
	      }
	    });
		} catch (IOException e) {
			// TODO - LOG ERROR
		}
		return filePath;
	}
	
}
