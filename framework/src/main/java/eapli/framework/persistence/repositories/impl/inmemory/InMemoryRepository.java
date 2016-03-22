package eapli.framework.persistence.repositories.impl.inmemory;

import eapli.framework.domain.DomainEntity;
import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.framework.persistence.repositories.Repository;

import java.io.Serializable;
import java.util.*;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepository<T extends DomainEntity<K>, K extends Serializable>
        implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

    List<T> repository = new ArrayList<T>();

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

    @Override
    public void deleteById(K entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator(int pagesize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T save(T entity) {
        repository.add(entity);
        return entity;
    }

    @Override
    public List<T> all() {
        return repository;
    }

    @Override
    public T findById(K id) {
        for (T item : repository) {
            if(item.id().equals(id)) {
                return item;
            }
        }
        //throw new NotFoundException("Not found exception");
        //FIXME do not return null
        return null;
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
}
