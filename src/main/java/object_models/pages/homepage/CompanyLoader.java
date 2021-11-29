/**
 * 
 */
package object_models.pages.homepage;

import entities.Company;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface CompanyLoader {
	Company getCurrentCompany();
	Company loadCompany(String compName);
}
