/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.application.meals.ListDishTypeController;
import eapli.ecafeteria.application.meals.ListMealTypeService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Diogo
 */
public class CheckExistingBookingController implements Controller {

    private final BookingRepository repository = PersistenceContext.repositories().bookings(null);
    private final ListBookingsService bookingsService = new ListBookingsService();

    //TODO preferably, controllers should not have state
    private Date date;
    private MealType mealTypeStr;
    private DishType dishType;

    public Iterable<Booking> checkBookingsByDateMealAndDishType(Calendar date, String mealType, DishType dishType) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final MealTypes mealTypee=null;
        if(mealType=="Almoco"){
            
            return this.repository.checkBookingsByDateMealAndDishType(date, new MealType(mealTypee.ALMOCO), dishType);
        } 
        else if (mealType.equalsIgnoreCase("Jantar")){
            
            return this.repository.checkBookingsByDateMealAndDishType(date, new MealType(mealTypee.JANTAR), dishType);
        }
        return this.repository.checkBookingsByDateMealAndDishType(date, new MealType(mealTypee), dishType);
        }
    
    /**
     * in the context of this use case only active dish types are meaningful.
     *
     * @return
     */
    public Iterable<DishType> listDishTypes() {
	return new ListDishTypeController().listDishTypes();
    }
    
    public Iterable<MealType.MealTypes> listMealTypes(){
        return new ListMealTypeService().allMealTypes();
    }
}
