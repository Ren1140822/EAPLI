/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteria.account;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.ValueObject;
import eapli.util.DateTime;

import javax.persistence.Embeddable;
import java.util.Calendar;

/**
 * It represents the balance limit of the account card.
 *
 * @author Sofia
 */
@Embeddable
public class BalanceLimit implements ValueObject {

    /**
     * Multiplication factor to be used in the balance validation
     */
    private int factor;

    /**
     * Constructs an instance of BalanceLimit
     */
    public BalanceLimit() {
        factor = Application.settings().getMultiplicationFactorForBalanceAlert();
    }

    /**
     * It checks if the balance limit is violated, seeing if it has enough
     * balance.
     *
     * @param b       The balance to be ocmpared.
     * @param average The balance average calculated.
     * @return It returns boolean if the average greater than the balance or
     * false otherwise.
     */
    public boolean isViolated(Balance b, Money average) {
        Money newAverage = average.multiply(factor);
        return !b.hasEnoughBalance(newAverage);
    }

    /**
     * It calculates the week average cost of all meals.
     *
     * @param user The cafeteria user.
     * @param rep
     * @return It returns the average.
     */
    public double currentWeekAverageCost(CafeteriaUser user, MenuRepository rep) {
        Calendar dateNow = DateTime.now();
        int week = DateTime.currentWeekNumber();
        int year = DateTime.currentYear();
        Calendar dateOfLastDayOfTheWeek = DateTime.endOfWeek(year, week);
        double sum = 0;
        double average = 0;
        int mealSize = 0;
        Iterable<Menu> list = rep.publishedMenu(user);

        for (Menu m : list) {
            Iterable<Meal> mealsList = m.getMeals();
            for (Meal meal : mealsList) {
                if (DateTime.isAfter(meal.getDate(), dateNow) && DateTime.isBefore(dateOfLastDayOfTheWeek, meal.getDate())) {
                    sum = sum + meal.dish().currentPrice().amount();
                    mealSize++;
                }
            }
        }
        if (mealSize == 0) {
            throw new IllegalStateException("There no meals available for the week.");
        }
        average = (double) sum / mealSize;
        return average;
    }
}
