/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Pedro Fernandes
 */
public class InMemoryMaterialUsedRepository extends InMemoryRepositoryWithLongPK<MaterialUsed> implements MaterialUsedRepository{

    @Override
    public Iterable<MaterialUsed> searchByLot(String lotCode) {
        return match(e -> (e.batchNumber().lotCode().equals(lotCode)));
    }
    
}
