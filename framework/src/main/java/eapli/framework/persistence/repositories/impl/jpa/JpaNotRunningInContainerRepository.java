/**
 *
 */
package eapli.framework.persistence.repositories.impl.jpa;

import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
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
public class JpaNotRunningInContainerRepository<T, K extends Serializable> 
	extends JpaBaseRepository<T, K> {

    private JpaTransactionalContext TxCtx;

    /**
     *
     */
    public JpaNotRunningInContainerRepository(TransactionalContext TxCtx) {
        super();
        setTxCtx(TxCtx);
    }
    
    JpaNotRunningInContainerRepository(TransactionalContext TxCtx, Class<T> classz) {
        super(classz);
        setTxCtx(TxCtx);
    }

    private void setTxCtx(TransactionalContext TxCtx) {
        if (TxCtx==null || !(TxCtx instanceof JpaTransactionalContext)) {
        	throw new IllegalArgumentException();
        }
        this.TxCtx = (JpaTransactionalContext)TxCtx;
    }
    
    @Override
    @SuppressWarnings("squid:S3346")
    protected EntityManagerFactory entityManagerFactory() {
    	return this.TxCtx.entityManagerFactory();
    }
    
    @Override
    protected EntityManager entityManager() {
        return this.TxCtx.entityManager();
    }
}
