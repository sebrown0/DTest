/**
 * 
 */
package object_models.helpers;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Steve Brown
 *
 * Retrieve the field values for a Class<?>
 */
public class ClassFieldGetter {
	private Class<?> clazz;
	private Logger logger = LogManager.getLogger();
	private Optional<String> parentName = Optional.empty();
	private Optional<String> menuItemName = Optional.empty();
	private Optional<String> elementId = Optional.empty();
	
	public ClassFieldGetter(Class<?> clazz) {
		this.clazz = clazz;
		setFields();
	}

	private void setFields() {
		setParentName();
		setMenuItemName();
		setElementId();
	}
	
	public void setParentName() {		
		try {
			parentName = Optional.ofNullable((String) clazz.getField("MENU_PARENT_NAME").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get parent name using reflection");
		}
	}
	
	public void setMenuItemName() {		
		try {
			menuItemName = Optional.ofNullable((String) clazz.getField("MENU_TITLE").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get menu name using reflection");
		}
	}
	
	public void setElementId() {		
		try {
			elementId = Optional.ofNullable((String) clazz.getField("PANEL_TITLE").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get element id using reflection");
		}
	}
		
	public Optional<String> getParentName() {
		return parentName;
	}
	public Optional<String> getMenuItemName() {
		return menuItemName;
	}
	public Optional<String> getElementId() {
		return elementId;
	}
}
