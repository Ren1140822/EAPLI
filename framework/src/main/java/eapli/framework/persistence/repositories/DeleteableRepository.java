/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.persistence.repositories;

import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Paulo Gandra Sousa
 */
public interface DeleteableRepository<T, K> extends Repository<T, K> {

    /**
     * removes the specified entity from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException if the delete operation makes no
     * sense for this repository
     */
    void delete(T entity) throws DataIntegrityViolationException;

    /**
     * Removes the entity with the specified primary key from the repository.
     *
     * @param entity
     * @throws DataIntegrityViolationException
     * @throws UnsuportedOperationException if the delete operation makes no
     * sense for this repository
     */
    void deleteByPK(K entityId) throws DataIntegrityViolationException;
}
