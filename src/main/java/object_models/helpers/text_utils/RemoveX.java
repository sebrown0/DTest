/**
 * 
 */
package object_models.helpers.text_utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve Brown
 *
 */
public class RemoveX implements TextSanitiser {

	@Override
	public List<String> sanitiseText(String txt) {
		List<String> goodLines = new ArrayList<>();
		String lines[] = txt.split("\\r?\\n");
		
		if(txt.length() > 0) {
			for (String s : lines) {				
				int c = (int) s.charAt(0);
				if(c != 215 && c != 120 && c != 88) {
					goodLines.add(s);
				} 	
			}	
		}		
		return goodLines;
	}

}
