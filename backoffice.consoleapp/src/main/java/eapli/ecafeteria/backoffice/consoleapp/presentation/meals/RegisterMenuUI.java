package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.application.meals.RegisterMenuController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.OrganicUnitPrinter;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class RegisterMenuUI extends AbstractUI {

    private final RegisterMenuController theController = new RegisterMenuController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        try{
            final Calendar today = Calendar.getInstance();
            final Calendar start = Calendar.getInstance();
            final Calendar end = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            final String startDateStr = Console.readLine("Period Start (dd/mm/yyyy)");
            start.setTime(sdf.parse(startDateStr));

            if(today.compareTo(start) > 0){
                System.out.printf("The start date cannot be in the past.\n");
                throw new IllegalArgumentException();
            }

            final String endDateStr = Console.readLine("Period End   (dd/mm/yyyy)");
            end.setTime(sdf.parse(endDateStr));

            if(start.compareTo(end) >= 0){
                System.out.printf("The end date cannot be in the past or the same date as the start.\n");
                throw new IllegalArgumentException();
            }else{
                long diff = end.getTime().getTime() - start.getTime().getTime();
                diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if(diff > 7){
                    System.out.printf("Difference between start and end date cannot be greater than a week.\n");
                    throw new IllegalArgumentException();
                }
            }

            final TimePeriod2 theTimePeriod2 = new TimePeriod2(start, end);

            final Iterable<MealType.MealTypes> mealTypes = this.theController.allMealTypes();
            final SelectWidget<MealType.MealTypes> selector = new SelectWidget<>("Meal types:", mealTypes, new MealTypePrinter());
            selector.show();
            final MealType theMealType = new MealType(selector.selectedElement());

            final Iterable<Dish> dishes = this.theController.allDishes();
            final SelectWidget<Dish> selector2 = new SelectWidget<>("Dishes:", dishes, new DishPrinter());
            selector2.show();
            final Dish theDish = selector2.selectedElement();

            final Iterable<OrganicUnit> organicUnits = this.theController.allOrganicUnits();
            final SelectWidget<OrganicUnit> selector3 = new SelectWidget<OrganicUnit>("Organic Units:", organicUnits, new OrganicUnitPrinter());
            selector3.show();
            final OrganicUnit organicUnit = selector3.selectedElement();

            try {
                this.theController.registerMenu(theDish, theMealType, organicUnit, theTimePeriod2, Calendar.getInstance());
                System.out.printf("Menu registered successfully.\n");
            } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                System.out.println("You tried to enter a dish which already exists in the database.");
            }
        }catch(ParseException e){
            System.out.println("The date you entered was invalid.");
        }catch(IllegalArgumentException e){
            // Do nothing
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
