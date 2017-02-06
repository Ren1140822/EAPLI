package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.framework.persistence.repositories.impl.jpa.JpaTxRepository;
import java.io.Serializable;

abstract class CafeteriaJpaRepositoryBase<T, K extends Serializable> extends JpaTxRepository<T, K> {

    public CafeteriaJpaRepositoryBase(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    public CafeteriaJpaRepositoryBase() {
        super(Application.settings().getPersistenceUnitName());
    }
}
