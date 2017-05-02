/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.persistence.repositories.impl.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Optional;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * @author pgsou_000
 */
public class JpaAutoTxRepository<T, K extends Serializable> implements DataRepository<T, K> {

    protected JpaBaseRepository<T, K> repo;
    private final TransactionalContext autoTx;

    public JpaAutoTxRepository(String persistenceUnitName, TransactionalContext autoTx) {
	final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	@SuppressWarnings("unchecked")
	final Class<T> entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

	if (autoTx == null) {
	    repo = new JpaTransactionalRepository<>(persistenceUnitName, entityClass);
	} else {
	    repo = new JpaNotRunningInContainerRepository<>(autoTx, entityClass);
	}
	this.autoTx = autoTx;
    }

    @Override
    public void delete(T entity) throws DataIntegrityViolationException {
	repo.delete(entity);
    }

    @Override
    public void delete(K id) throws DataIntegrityViolationException {
	repo.delete(id);
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
}
