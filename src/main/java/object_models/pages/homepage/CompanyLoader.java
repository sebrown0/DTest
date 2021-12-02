/**
 * 
 */
package object_models.pages.homepage;

import entities.company.Company;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface CompanyLoader {
	Company getCurrentCompany();
	HomePage loadCompany(Company co);
}
