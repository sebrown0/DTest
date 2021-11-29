/**
 * 
 */
package utils;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * @param T the object to get the value from. *  
 * @param V the value to look for.
 * 
 * Functional interface to test if object is in list.
 * 
 */
public interface ListTest <T extends Object, V extends Object>{
	boolean test(T t, V v);
}
