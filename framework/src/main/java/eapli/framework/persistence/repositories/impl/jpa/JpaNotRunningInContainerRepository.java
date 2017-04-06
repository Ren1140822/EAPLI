/**
 *
 */
package eapli.framework.persistence.repositories.impl.jpa;

import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * An utility class for implementing JPA repositories not running in containers.
 * as such, this class creates its own EntityManagerFactory instead of using an
 * injected EMF by the container. This classes assumes that transaction
 * management is done outside of the class so callers of the repository must
 * first initiate a transaction and explicitly end it; typically this will be a
 * responsibility of the controller by calling the methods of the
 * TransactionalContext interface.
 *
 * @author Paulo Gandra Sousa
 * @param <T> the entity type managed by this repository (a table in the
 * database)
 * @param <K> the primary key of the table
 */
public class JpaNotRunningInContainerRepository<T, K extends Serializable> extends JpaBaseRepository<T, K>
        implements TransactionalContext {

    private final String persistenceUnitName;
    private static EntityManagerFactory singletonEMF;

    /**
     *
     * @param persistenceUnitName the name of the persistence unit to use
     */
    public JpaNotRunningInContainerRepository(String persistenceUnitName) {
        super();
        this.persistenceUnitName = persistenceUnitName;
    }

    JpaNotRunningInContainerRepository(String persistenceUnitName, Class<T> classz) {
        super(classz);
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    @SuppressWarnings("squid:S3346")
    protected EntityManagerFactory entityManagerFactory() {
        if (singletonEMF == null) {
            assert !Strings.isNullOrEmpty(persistenceUnitName) : "the persistence unit name must be provided";
            Logger.getLogger(this.getClass().getSimpleName()).info("Not runing in container mode.");
            singletonEMF = Persistence.createEntityManagerFactory(persistenceUnitName);
        }
        return singletonEMF;
    }

    @Override
    public void beginTransaction() {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
    }

    @Override
    public void commit() {
        entityManager().getTransaction().commit();
    }

    @Override
    public void rollback() {
        entityManager().getTransaction().rollback();
    }

    @Override
    public void close() {
        entityManager().close();
    }
}
