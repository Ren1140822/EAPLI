/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import eapli.util.DateTime;

import java.util.Calendar;
import java.util.Date;

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
    public Iterable<Meal> findByDate(TimePeriod2 period) {
        return match(e -> e.getDate().compareTo(period.start()) > 0 && e.getDate().compareTo(period.end()) < 0);
    }

}
