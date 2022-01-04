/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;
import java.io.Writer;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ClassWriterActions {
	Writer getWriter();
	void writeNewLines(int numLines) throws IOException;
	void writeNewLine() throws IOException; 
	void addTab() throws IOException; 
}
