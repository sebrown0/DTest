/**
 * 
 */
package helpers;

import exceptions.IncorrectPageException;

/**
 * @author SteveBrown
 *
 */
public interface LoadPage {
	void loadUsingURI() throws IncorrectPageException;
}
