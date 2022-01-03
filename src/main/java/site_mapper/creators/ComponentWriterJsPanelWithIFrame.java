/**
 * 
 */
package site_mapper.creators;

import java.util.Arrays;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ComponentWriterJsPanelWithIFrame implements JsPanelComponentWriter{

	@Override //ComponentWriter
	public List<String> getImportNames() {
		return Arrays.asList("JsPanelWithIFrame","CoreData");
	}

	@Override //ComponentWriter
	public List<String> getSuperArgs() {
		return Arrays.asList("coreData", "PANEL_TITLE");
	}
	
	@Override //ComponentWriter
	public List<String> getConstructorArgs() {
		return Arrays.asList("CoreData coreData");
	}

	@Override
	public List<String> getNamesAndTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
