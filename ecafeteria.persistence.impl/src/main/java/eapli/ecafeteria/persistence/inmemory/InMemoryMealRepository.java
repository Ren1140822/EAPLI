/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Eduangelo Ferreira
 */
public class InMemoryMealRepository extends InMemoryRepositoryWithLongPK<Meal> implements MealRepository {

    @Override
    public Meal findByPk(Long pk) {
        return matchOne(e->e.pk().equals(pk));
    }

    @Override
    public Iterable<Meal> findByDate(TimePeriod2 timePeriod) {
        return match(e -> e.timePeriod().equals(timePeriod));
    }

}
