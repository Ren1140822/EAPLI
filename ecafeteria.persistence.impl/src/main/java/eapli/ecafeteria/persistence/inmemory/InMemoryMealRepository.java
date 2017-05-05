/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;


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
    public Iterable<Meal> findByDate(Calendar date) {
        return match(e -> e.getDate().compareTo(date) > 0);
    }

    @Override
    public Iterable<Meal> findByDateAndMealType(Calendar date, MealType.MealTypes type) {
        return match(e -> (e.getDate().compareTo(date) > 0 && e.mealType().equals(type)));
    }

}
