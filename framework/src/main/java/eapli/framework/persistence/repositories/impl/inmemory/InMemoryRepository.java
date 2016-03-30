package eapli.framework.persistence.repositories.impl.inmemory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eapli.framework.domain.DomainEntity;
import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.framework.persistence.repositories.Repository;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepository<T extends DomainEntity<K>, K extends Serializable>
        implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

    private final List<T> repository = new ArrayList<T>();

    @Override
    public void delete(T entity) {
        this.repository.remove(entity);
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
        this.repository.add(entity);
        return entity;
    }

    @Override
    public List<T> all() {
        return this.repository;
    }

    /**
     * This method is used for searching a list without using Optional and
     * Streams, thus returning null when no element is found.
     *
     * @param id
     *            K identifier for object
     * @return T if object identified by K is found, otherwise returns null.
     */
    @Override
    public T findById(K id) {
        for (final T item : this.repository) {
            if (item.id().equals(id)) {
                return item;
            }
        }
        // FIXME do not return null
        return null;
    }

    /**
     * This method is only used for showing the usage of Optional and streams to
     * avoid returning null. In either case, the client code must check for
     * NoSuchElementException.
     *
     * @param id
     *            Identifier to look for
     * @return T
     */
    /*
     * @Override public T findById(K id) { Optional<T> value =
     * repository.stream().filter(a -> a.id().equals(id)).findFirst(); T t=
     * value.get(); return t; }
     */
    @Override
    public long size() {
        return this.repository.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.repository.iterator();
    }

    @Override
    public boolean add(T entity) {
        return this.repository.add(entity);
    }
}
