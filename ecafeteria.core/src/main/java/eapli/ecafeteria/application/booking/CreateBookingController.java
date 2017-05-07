/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.*;
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
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);

    public List<Meal> menusOfDay(Calendar day) {
        List<Meal> mealsOfDay = new LinkedList<>();
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        Iterable<Menu> menus = menuRepository.publishedMenusOfDay(day,user);
        Calendar cal = Calendar.getInstance();
        for(Menu m: menus){
            for (Meal meal : m.getMeals()) {
                Calendar mealDate = meal.getDate();
                if(mealDate.get(Calendar.DAY_OF_MONTH)==cal.get(Calendar.DAY_OF_MONTH)
                   && mealDate.get(Calendar.YEAR)==cal.get(Calendar.YEAR)
                   && mealDate.get(Calendar.MONTH)==cal.get(Calendar.MONTH)){
                    if(cal.get(Calendar.HOUR_OF_DAY)<meal.mealType().freeBookingCancellationTimeLimit().get(Calendar.HOUR_OF_DAY)){
                        mealsOfDay.add(meal);
                    }
                }else{
                    mealsOfDay.add(meal);
                }
            }
        }
        return mealsOfDay;
    }

    public void book(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        //FIXME there is no need for transactions if we are just querying the data
        TxCtx.beginTransaction();
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        TxCtx.commit();
        registerBooking(user, meal);
    }

    public Booking registerBooking(CafeteriaUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        Booking b = new Booking(user, meal);
        return bookingRepository.save(b);
    }
    
    public Calendar transformDate(String dayToBook){
        int year, month, day;
        String tokens[] = dayToBook.split("-");
        year = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        day = Integer.parseInt(tokens[2]);
        return DateTime.newCalendar(year, month, day);
    }

}
