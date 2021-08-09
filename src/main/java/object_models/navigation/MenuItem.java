/**
 * 
 */
package object_models.navigation;

/**
 * @author Steve Brown
 *
 */
public class MenuItem {
	MenuItem parent;
	String name;
	String href;
	
	public MenuItem(String name) {		
		this.name = name;
	}
	
	public MenuItem(String name, MenuItem parent) {		
		this.parent = parent;
		this.name = name;		
	}
		
	public MenuItem(String name, MenuItem parent, String href) {
		this.parent = parent;
		this.name = name;
		this.href = href;
	}
	
	public MenuItem getParent() {
		return parent;
	}
	public void setParent(MenuItem parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	public String getParentName() {
		if (parent != null) {
			return parent.getName();
		}else {
			return "";
		}			
	}
	
	@Override
	public String toString() {
		return "MenuElement [parent name=" + getParentName() + ", name=" + name + ", href=" + href + "]";
	}
	
}
