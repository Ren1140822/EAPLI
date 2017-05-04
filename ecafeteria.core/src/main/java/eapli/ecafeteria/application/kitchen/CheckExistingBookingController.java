/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Date;

/**
 *
 * @author Diogo
 */
public class CheckExistingBookingController implements Controller {

    private final BookingRepository repository = PersistenceContext.repositories().bookings();
    private final ListBookingsService bookingsService = new ListBookingsService();
    private Date date;
    private MealType mealType;
    private DishType dishType;

    public Iterable<Booking> checkBookingsByDateMealAndDishType() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.repository.checkBookingsByDateMealAndDishType(date, mealType, dishType);
    }

}
