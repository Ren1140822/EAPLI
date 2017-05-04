/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.TimePeriod2;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduangelo Ferreira
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Meal findByPk(Long pk) {
        return matchOne("e.pk=pk", "pk", pk);
    }

    @Override
    public Iterable<Meal> findByDate(TimePeriod2 timePeriod) {
        Map<String, Object> params = new HashMap<>();
        params.put("timePeriod", timePeriod);
        return match("e.timePeriod=:timePeriod", params);
    }



}
