/**
 * 
 */
package object_models.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 * This is used when the element does not exist but a service is expecting to consume one.
 * 
 */
public class NoElement implements Closable, ElementDoesNotExist{
	private String elementName;
		
	public NoElement(String elementName) {
		this.elementName = elementName;
		logError();
	}

	@Override
	public void logError() {
		Logger logger = LogManager.getLogger();
		logger.error("The element [" + elementName + "]" + " does not exist");
	}

	@Override
	public void closeElement() {
		// TODO Auto-generated method stub
		
	}

}
