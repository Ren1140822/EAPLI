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

    private final CafeteriaUserRepository cafuserRepository = PersistenceContext.repositories().cafeteriaUsers(TxCtx);
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(TxCtx);
    private final TransactionRepository transactionsRepository = PersistenceContext.repositories().transactions(TxCtx);
    private final AccountCardRepository accountRepository = PersistenceContext.repositories().accountCards(TxCtx);
    


    public List<Meal> menusOfDay(Date dateTime) {
        Calendar day = Calendar.getInstance();
        day.setTime(dateTime);
        List<Meal> mealsOfDay = new LinkedList<>();
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        
        Iterable<Menu> menus = menuRepository.publishedMenusOfDay(day, user);
        Calendar cal = Calendar.getInstance();
        for (Menu m : menus) {
            for (Meal meal : m.getMeals()) {
                Calendar mealDate = meal.getDate();
                if (DateTime.isSameDate(mealDate, day)) {
                    if ( DateTime.isSameDate(mealDate, cal)) {
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
    public void save(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException{
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        registerBooking(user,meal);
    }

    public Booking registerBooking(CafeteriaUser user,Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        
        Booking booking = new Booking(user, meal);
        Transaction purchase = booking.purchase();
        purchase.notifyObservers();
        TxCtx.beginTransaction();
        bookingRepository.save(booking);
        transactionsRepository.save(purchase);
        TxCtx.commit();
        return booking ;
    }
    
    public boolean hasEnoughMoney(Meal meal){
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
         Money price = meal.dish().currentPrice();
        AccountCard card = accountRepository.findByMecanographicNumber(user.mecanographicNumber());
        Balance balance = card.balance();
        if(balance.amount().lessThan(price)) return false;
        return true;
    }



}
