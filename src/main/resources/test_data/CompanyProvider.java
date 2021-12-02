/**
 * 
 */
package test_data;

import java.util.stream.Stream;

import entities.company.Company;

/**
 * @author Steve Brown
 *
 */
public class CompanyProvider {
	private static final Company DEFAULT_CO = new Company("Mars Northern Products Ltd");
	
	public static Company defaultCompany() {
		return DEFAULT_CO;
	}
	
	public static Company marsNorthernProductsLtd() {
		return DEFAULT_CO;
	}
	
	public static Company marsIncorporatedProductsLtd() {
		return new Company("Mars Incorporated Ltd");
	}
	
	public static Stream<Company> allCompanies(){
		return Stream.of(
				DEFAULT_CO, 
				new Company("Mars Incorporated Ltd"));
	}
	
	
}
