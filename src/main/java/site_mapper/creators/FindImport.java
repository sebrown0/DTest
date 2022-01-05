/**
 * 
 */
package site_mapper.creators;

import utils.FileFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class FindImport implements ImportType{
private String importStr;
	
	public FindImport(String importStr) {
		this.importStr = importStr;
	}
	
	@Override
	public String getPath() {
		String 
			importPath = FileFinder
				.findPathWithoutRootAndExtension("./src/main/java", importStr + ".java")
				.replaceAll("\\\\", "."); 

		return "import " + importPath + ";";				
	}

}
