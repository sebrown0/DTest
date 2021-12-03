/**
 * 
 */
package entities.company;

import java.util.Optional;

import org.apache.logging.log4j.Logger;

import object_models.left_nav_bar.LeftNavBar;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Add try catch to loadCompany().
 * @version 1.2
 *  Remove logic checking current company status.
 *  Once we get to this stage we're loading the company, if it exists.
 * @since 1.0
 * 
 * Load the required company from the left nav-bar.
 * 
 */
public class LoaderCompany {
	private Company forCompany;
	private LeftNavBar leftNavBar;
	private Logger logger;
	
	public LoaderCompany(Company forCompany, CoreData coreData, LeftNavBar leftNavBar) {
		this.forCompany = forCompany;
		this.leftNavBar = leftNavBar;
		this.logger = coreData.getLogger();
	}
	
	public Optional<Company> loadCompany() {		
		if(forCompany != null) {
			try {
				return Optional.ofNullable(leftNavBar.loadCompany(forCompany.getName()));	
			} catch (Exception e) {
				logger.error("Error loading company");
				return Optional.empty();
			}		
		}
		return null;			
	}

}
