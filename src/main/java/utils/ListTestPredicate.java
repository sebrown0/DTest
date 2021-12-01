/**
 * 
 */
package utils;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * @param T - object in list.  
 * 
 * Functional interface to test if object is in list.
 * 
 */
public interface ListTestPredicate <T extends Object>{
	boolean test(T t);
}
