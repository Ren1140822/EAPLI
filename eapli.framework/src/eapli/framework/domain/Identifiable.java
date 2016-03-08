/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

/**
 * a generic interface for checking if an object is identified by a certain T
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 */
public interface Identifiable<T> {

    /**
     * checks if the object is identified by the passed business id
     *
     * @param id the identity to check
     * @return true if the object has that identity
     */
    public boolean is(T id);

    public T id();
}
