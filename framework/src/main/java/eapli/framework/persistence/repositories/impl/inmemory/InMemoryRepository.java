package eapli.framework.persistence.repositories.impl.inmemory;

import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.lang.reflect.ParameterizedType;
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
        implements DataRepository<T, K>, TransactionalContext {

    // Ideally this would be a typed generic Map but since it is a static member
    // it cannot be generic. the solution is to use the old-style untyped Map
    // and cast whenever needed
    private static final Map DATA = new HashMap();
    private Class<T> entityClass;

    public InMemoryRepository() {
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    protected Map<K, T> data() {
        if (!this.DATA.containsKey(entityClass)) {
            this.DATA.put(entityClass, new HashMap());
        }
        return ((Map<K, T>) this.DATA.get(entityClass));
    }

    @Override
    public T first() {
        final Iterator<T> it = data().values().iterator();
        return it.hasNext() ? it.next() : null;
    }

    @Override
    public Iterable<T> first(int n) {
        final List<T> ret = new ArrayList<>();
        final Iterator<T> it = data().values().iterator();
        while (n > 0 && it.hasNext()) {
            ret.add(it.next());
        }
        return ret;
    }

    @Override
    public void delete(T entity) {
        for (Entry<K, T> each : data().entrySet()) {
            if (each.getValue().equals(entity)) {
                data().remove(each.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteByPK(K entityId) {
        data().remove(entityId);
    }

    @Override
    public Iterator<T> iterator(int pagesize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T save(T entity) {
        data().put(newPK(entity), entity);
        return entity;
    }

    @Override
    public Iterable<T> findAll() {
        return data().values();
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
        return Optional.ofNullable(data().get(id));
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
     * DATA.stream().filter(a -> a.id().equals(id)).findFirst(); T t=
     * value.get(); return t; }
     */
    @Override
    public long count() {
        return data().size();
    }

    @Override
    public Iterator<T> iterator() {
        return data().values().iterator();
    }

    protected abstract K newPK(T entity);

    protected Iterable<T> match(Predicate<T> filterFunc) {
        return data().values().stream().filter(filterFunc).collect(Collectors.toList());
    }

    protected T matchOne(Predicate<T> filterFunc) {
        // TODO if there is no element an IndexOutOfBounds exception will be
        // thrown. most likely we should return null
        return data().values().stream().filter(filterFunc).collect(Collectors.toList()).get(0);
    }

    @Override
    public void beginTransaction() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //just ignore...
    }

    @Override
    public void commit() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //just ignore...
    }

    @Override
    public void rollback() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //just ignore...
    }

    @Override
    public void close() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //just ignore...
    }
}
