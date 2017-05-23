/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PC
 */
public class AccumulatedCaloricConsumptionService {
        private final CafeteriaUserService userService = new CafeteriaUserService();
    private final BookingRepository repo = PersistenceContext.repositories().bookings(null);
    
    public Integer calculateAcumulateCaloricConsumption(Meal meal){
       int weekNumber =  DateTime.weekNumber(meal.getDate());
       Calendar startWeek = DateTime.beginningOfWeek(meal.getDate().get(Calendar.YEAR), weekNumber);
       CafeteriaUser user = userService.findCafeteriaUserByUsername(Application.session().session().authenticatedUser().id());
       Iterable<Booking> bookingsDone = repo.findBookingByDateAndStateAndUser(startWeek,meal.getDate(),user,BookingState.DONE) ;
       Iterable<Booking> bookingsConsumed = repo.findBookingByDateAndStateAndUser(startWeek,meal.getDate(),user,BookingState.DEFINITIVE);
       int caloricConsumed=meal.dish().nutricionalInfo().calories();
        for (Booking booking : bookingsConsumed) {
            caloricConsumed=caloricConsumed+booking.meal().dish().nutricionalInfo().calories();
        }
        for (Booking booking : bookingsDone) {
            caloricConsumed=caloricConsumed+booking.meal().dish().nutricionalInfo().calories();
        }
       return caloricConsumed;
    } 
}
