/**
 * 
 */
package object_models.helpers.title;

/**
 * @author Steve Brown
 *
 */
public interface PageTitle {
	void setExpected(String expectedTitle);
	String getExpected();
	String getActual();	
}
