package eapli.ecafeteria.persistence.inmemory;


import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;


/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryDishTypeRepository extends InMemoryRepository<DishType, Long> implements DishTypeRepository {

    long nextID = 1;

    @Override
    protected Long newPK(DishType entity) {
        return ++nextID;
    }   
    
    }
