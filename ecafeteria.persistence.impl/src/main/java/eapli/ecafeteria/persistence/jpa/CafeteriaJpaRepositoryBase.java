package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.framework.persistence.repositories.impl.jpa.JpaNoContainerBaseRepository;
import java.io.Serializable;

abstract class CafeteriaJpaRepositoryBase<T, K extends Serializable> extends JpaNoContainerBaseRepository<T, K> {

    CafeteriaJpaRepositoryBase(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    CafeteriaJpaRepositoryBase() {
        super(Application.settings().getPersistenceUnitName());
    }
}
