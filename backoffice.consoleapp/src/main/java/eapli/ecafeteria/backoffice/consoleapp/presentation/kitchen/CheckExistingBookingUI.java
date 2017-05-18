/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckExistingBookingController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.*;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author João
 */
public class CheckExistingBookingUI extends AbstractUI {

    private final CheckExistingBookingController theController = new CheckExistingBookingController();

    protected Controller controller() {
	return this.theController;
    }
    
    
    @Override
    
    protected boolean doShow(){
                
        Calendar date = Console.readCalendar("dd-MM-yyyy");

        
        //mealType=Console.readLine("Insert meal Type:");
        
        System.out.println("Select dish Type:");
        final Iterable<DishType> dishTypes = this.theController.listDishTypes();
	final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", dishTypes, new DishTypePrinter());
	selector.show();
	final DishType dishType = selector.selectedElement();
        
//        System.out.println("Select dish Type:");
//        final Iterable<MealType.MealTypes> mealTypes = this.theController.listMealTypes();
//	final SelectWidget<MealType.MealTypes> selectorMeal = new SelectWidget<>("Meal types:", mealTypes, new MealTypePrinter());
//	selector.show();
//	final MealType.MealTypes mealType = selectorMeal.selectedElement();
        String mealType;
        do
        {
            mealType = Console.readLine("Insert meal type (Lunch/Dinner/Both):");   
            
        } while (!mealType.equalsIgnoreCase("Jantar") || 
                !mealType.equalsIgnoreCase("Almoco") || 
                !mealType.equalsIgnoreCase("Both")
                );
        
        Iterable<Booking> list = theController.checkBookingsByDateMealAndDishType(date, mealType, dishType);
        
        ListWidget<Booking> lister = new ListWidget<>("Bookings", list, new BookingPrinter());
        lister.show();
        
        Console.waitForKey("Press Enter to return.");
        return true;
    }

    @Override
    public String headline() {
        return "Existing Bookings";
    }
    
}