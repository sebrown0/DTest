/**
 * 
 */
package object_models.helpers;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.helpers.Formatter;

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
	private Optional<String> originalName = Optional.empty();
	
	public ClassFieldGetter(Class<?> clazz) {
		this.clazz = clazz;
		setFields();
	}

	private void setFields() {
		setParentName();
		setMenuItemName();
		setPanelTitle();
		setOriginalName();
	}
	
	private void setParentName() {		
		try {
			parentName = Optional.ofNullable((String) clazz.getField("MENU_PARENT_NAME").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get parent name using reflection");
		}
	}
	
	private void setMenuItemName() {		
		try {
			menuItemName = Optional.ofNullable((String) clazz.getField("MENU_TITLE").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get menu name using reflection");
		}
	}
	
	private void setPanelTitle() {		
		try {
			elementId = Optional.ofNullable((String) clazz.getField("PANEL_TITLE").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get element id using reflection");
		}
	}
	
	private void setOriginalName() {		
		try {
			originalName = Optional.ofNullable((String) clazz.getField("ORIGINAL_NAME").get(null));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			logger.error("Failed to get element original using reflection");
		}
	}
		
	public Optional<String> getParentName() {
		return parentName;
	}
	public Optional<String> getMenuItemName() {
		if(menuItemName.isPresent()) {
			return Optional.ofNullable(Formatter.capitaliseFirstChar(menuItemName.get()));	
		}else {
			return Optional.empty();
		}
		
	}
	public Optional<String> getPanelTitle() {
		return elementId;
	}
	public Optional<String> getOriginalName() {
		return originalName;
	}
}
