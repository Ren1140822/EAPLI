/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

/**
 * a generic interface for checking if an object is identified by a certain ID
 *
 * @author Paulo Gandra Sousa
 * @param <ID>
 */
public interface Identifiable<ID> {

    /**
     * checks if the object is identified by the passed ID
     *
     * @param id the identity to check
     * @return true if the object has that identity
     */
    public boolean is(ID id);

    public ID id();
}
