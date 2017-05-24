/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro Fernandes
 */
public class JpaMaterialUsedRepository extends CafeteriaJpaRepositoryBase<MaterialUsed, Long> implements MaterialUsedRepository {

    @Override
    public Iterable<MaterialUsed> searchByLot(String lotCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("lotCode", lotCode);
        return match("e.BatchNumber.lotCode=:lotCode");
    }

}
