/**
 * 
 */
package controls;

import java.util.List;

/**
 * @author Steve Brown
 *
 * Given a MappingStrategy (list of controls to map),
 */
public class PageMapper {
	private List<MapControl> controlMappers;
	private PageMap pageMap = new PageMap();
	
	public PageMapper(MappingStrategy mappingStrategy) {
		this.controlMappers = mappingStrategy.getList();
	}
	
	public PageMapper mapControls() {
		for (MapControl mapControl : controlMappers) {			
			addControlToPageMap(mapControl);
		}
		return this;
	}
	
	private void addControlToPageMap(MapControl mc) {		
		mc.addToPageMap(pageMap);
	}
	
	public PageMap getPageMap() {
		return pageMap;
	}
}
