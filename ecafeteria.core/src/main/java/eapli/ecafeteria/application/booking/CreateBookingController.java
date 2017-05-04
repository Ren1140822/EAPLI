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
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.*;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;

import java.util.Calendar;
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
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings();

    public List<Meal> menusOfDay(Calendar day){
        /*List<Meal> mealsOfDay = new LinkedList<>();
        Iterable<Menu> menus = menuRepository.publishedMenusOfDay(day);
        for(Menu m: menus){
            if( (m.getMenuEntry().timePeriod().start().before(day) || m.menuEntry().timePeriod().start().equals(day))
                    && (m.menuEntry().timePeriod().end().after(day) || m.menuEntry().timePeriod().start().equals(day) )){
                mealsOfDay.add(new Meal(m.menuEntry().dish(), m.menuEntry().mealType(), new TimePeriod2(day,day)));
            }
        }*/
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void book(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        TxCtx.beginTransaction();
        CafeteriaUser user = cafuserRepository.findByUsername(Application.session().session().authenticatedUser().id());
        TxCtx.commit();
        registerBooking(user, meal);
    }

    public void registerBooking(CafeteriaUser user, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        Booking b = new Booking(user, meal, BookingState.DONE);
        bookingRepository.save(b);
    }
    
}
