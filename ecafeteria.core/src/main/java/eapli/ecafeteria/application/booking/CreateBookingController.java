/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.domain.cafeteria.account.Balance;
import eapli.ecafeteria.domain.cafeteria.account.Transaction;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.*;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.util.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PC
 */
public class CreateBookingController {

    private final TransactionalContext TxCtx
            = PersistenceContext.repositories().buildTransactionalContext();

    //FIXME
    //@author Meireles
    // Is the CafeteriaUserRepository necessary? And does it need to have external transactional context?
    // I suggest using the method "findCafeteriaUserByUsername" from the CafeteriaUserService class.
    private final CafeteriaUserRepository cafuserRepository = PersistenceContext.repositories().cafeteriaUsers(TxCtx);
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(TxCtx);
    private final TransactionRepository transactionsRepository = PersistenceContext.repositories().transactions(TxCtx);
    private final AccountCardRepository accountRepository = PersistenceContext.repositories().accountCards(TxCtx);

    public List<Meal> menusOfDay(Date dateTime) {
        //FIXME
        //@author Meireles
        // Check method "dateToCalendar" from DateTime class.
        Calendar day = Calendar.getInstance();
        day.setTime(dateTime);
        List<Meal> mealsOfDay = new LinkedList<>();
        //FIXME
        //@author Meireles
        // Is the CafeteriaUserRepository necessary? And does it need to have external transactional context?
        // I suggest using the method "findCafeteriaUserByUsername" from the CafeteriaUserService class.
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());

        Iterable<Menu> menus = menuRepository.publishedMenusOfDay(day, user);
        //FIXME
        //@author Meireles
        // Check method "now" from DateTime class.
        // I believe this object is not necessary since you can you methods
        // from DateTime class like "isToday" and "isUntilNow"
        Calendar cal = Calendar.getInstance();
        for (Menu m : menus) {
            for (Meal meal : m.getMeals()) {
                Calendar mealDate = meal.getDate();
                //TODO
                //@author Meireles
                // Can this code be changed to a private method like "isMealOfTheDay" and called here?
                // It would make the code easier to maintain.
                if (DateTime.isSameDate(mealDate, day)) {
                    if (DateTime.isSameDate(mealDate, cal)) {
                        //FIXME
                        //@author Meireles
                        // Check method "isBeforeTime" from DateTime class.
                        if (cal.get(Calendar.HOUR_OF_DAY) < meal.mealType().freeBookingCancellationTimeLimit().get(Calendar.HOUR_OF_DAY)) {
                            mealsOfDay.add(meal);
                        }
                    } else {
                        mealsOfDay.add(meal);
                    }
                }
            }
        }
        return mealsOfDay;
    }

    public void save(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        //FIXME
        //@author Meireles
        // Is the CafeteriaUserRepository necessary? And does it need to have external transactional context?
        // I suggest using the method "findCafeteriaUserByUsername" from the CafeteriaUserService class.
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        registerBooking(user, meal);
    }

    //FIXME
    //@author Meireles
    // Should this method be public? The UI does not call it.
    // I suggest joining it with the method "save".
    public Booking registerBooking(CafeteriaUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {

        Booking booking = new Booking(user, meal);
        Transaction purchase = booking.purchase();
        purchase.notifyObservers();
        TxCtx.beginTransaction();
        bookingRepository.save(booking);
        transactionsRepository.save(purchase);
        TxCtx.commit();
        return booking;
    }

    public boolean hasEnoughMoney(Meal meal) {
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        Money price = meal.dish().currentPrice();
        AccountCard card = accountRepository.findByMecanographicNumber(user.mecanographicNumber());
        Balance balance = card.balance();
        //FIXME
        //@author Meireles
        // Should the balance has the responsability to tell if it can pay a certain amount?
        // I suggest balance should have a method like "boolean canPurchase(Meal meal)"
        // The if statement is redundant, return the condition itself.
        if (balance.amount().lessThan(price)) {
            return false;
        }
        return true;
    }

}
