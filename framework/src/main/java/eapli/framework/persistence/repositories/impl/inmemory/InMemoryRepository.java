package eapli.framework.persistence.repositories.impl.inmemory;

import eapli.framework.persistence.repositories.DeleteableRepository;
import eapli.framework.persistence.repositories.IterableRepository;
import eapli.framework.persistence.repositories.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by nuno on 20/03/16.
 */
public abstract class InMemoryRepository<T, K>
        implements Repository<T, K>, IterableRepository<T, K>, DeleteableRepository<T, K> {

    protected final Map<K, T> repository = new HashMap<>();

    @Override
    public T first() {
        final Iterator<T> it = this.repository.values().iterator();
        return it.hasNext() ? it.next() : null;
    }

    @Override
    public Iterable<T> first(int n) {
        final List<T> ret = new ArrayList<>();
        final Iterator<T> it = this.repository.values().iterator();
        while (n > 0 && it.hasNext()) {
            ret.add(it.next());
        }
        return ret;
    }

    @Override
    public void delete(T entity) {
        for (Entry<K, T> each : this.repository.entrySet()) {
            if (each.getValue().equals(entity)) {
                this.repository.remove(each.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteByPK(K entityId) {
        this.repository.remove(entityId);
    }

    @Override
    public Iterator<T> iterator(int pagesize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T save(T entity) {
        this.repository.put(newPK(entity), entity);
        return entity;
    }

    @Override
    public Iterable<T> findAll() {
        return this.repository.values();
    }

    /**
     * This method is used for searching a list without using Optional and
     * Streams, thus returning null when no element is found.
     *
     * @param id K identifier for object
     * @return T if object identified by K is found, otherwise returns null.
     */
    @Override
    public Optional<T> findOne(K id) {
        return Optional.ofNullable(this.repository.get(id));
    }

    /**
     * This method is only used for showing the usage of Optional and streams to
     * avoid returning null. In either case, the client code must check for
     * NoSuchElementException.
     *
     * @param id Identifier to look for
     * @return T
     */
    /*
     * @Override public T findById(K id) { Optional<T> value =
     * repository.stream().filter(a -> a.id().equals(id)).findFirst(); T t=
     * value.get(); return t; }
     */
    @Override
    public long count() {
        return this.repository.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.repository.values().iterator();
    }

    protected abstract K newPK(T entity);

    protected Iterable<T> match(Predicate<T> filterFunc) {
        return this.repository.values().stream().filter(filterFunc).collect(Collectors.toList());
    }

    protected T matchOne(Predicate<T> filterFunc) {
        // TODO if there is no element an IndexOutOfBounds exception will be
        // thrown. most likely we should return null
        return this.repository.values().stream().filter(filterFunc).collect(Collectors.toList()).get(0);
    }
}
