/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Optional;

/**
 *
 * @author pgsou_000
 */
public class JpaAutoTxRepository<T, K extends Serializable> implements DataRepository<T, K>, TransactionalContext {

    protected JpaBaseRepository<T, K> repo;
    private boolean autoTx;

    public JpaAutoTxRepository(String persistenceUnitName, boolean autoTx) {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

        if (autoTx) {
            repo = new JpaTransactionalRepository<T, K>(persistenceUnitName, entityClass);
        } else {
            repo = new JpaNotRunningInContainerRepository<T, K>(persistenceUnitName, entityClass);
        }
        this.autoTx = autoTx;
    }

    @Override
    public void delete(T entity) throws DataIntegrityViolationException {
        repo.delete(entity);
    }

    @Override
    public void deleteByPK(K id) throws DataIntegrityViolationException {
        repo.deleteByPK(id);
    }

    @Override
    public Iterator<T> iterator(int pagesize) {
        return repo.iterator(pagesize);
    }

    @Override
    public T first() {
        return repo.first();
    }

    @Override
    public Iterable<T> first(int n) {
        return repo.first(n);
    }

    @Override
    public T save(T entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return repo.save(entity);
    }

    @Override
    public Iterable<T> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<T> findOne(K id) {
        return repo.findOne(id);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public Iterator<T> iterator() {
        return repo.iterator();
    }

    @Override
    public void beginTransaction() {
        if (!autoTx) {
            ((JpaNotRunningInContainerRepository) repo).beginTransaction();
        }
    }

    @Override
    public void commit() {
        if (!autoTx) {
            ((JpaNotRunningInContainerRepository) repo).commit();
        }
    }

    @Override
    public void rollback() {
        if (!autoTx) {
            ((JpaNotRunningInContainerRepository) repo).rollback();
        }
    }

    @Override
    public void close() {
        if (!autoTx) {
            ((JpaNotRunningInContainerRepository) repo).close();
        }
    }
}
