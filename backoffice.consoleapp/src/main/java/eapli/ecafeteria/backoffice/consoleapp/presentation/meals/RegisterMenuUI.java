package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.application.meals.RegisterMenuController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.OrganicUnitPrinter;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.DateTime;
import eapli.util.io.Console;
import org.eclipse.persistence.internal.helper.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
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
        try {
            final Calendar today = Calendar.getInstance();
            final Calendar start = Calendar.getInstance();
            final Calendar end = Calendar.getInstance();

            start.setTime(Console.readDate("Period Start (dd/mm/yyyy)", "dd/MM/yyyy"));

            if (today.after(start)) {
                System.out.printf("The start date cannot be in the past.\n");
                return false;
            }

            end.setTime(Console.readDate("Period End (dd/mm/yyyy)", "dd/MM/yyyy"));

            if (start.after(end)) {
                System.out.printf("The end date cannot be in the past or the same date as the start.\n");
                return false;
            }

            final TimePeriod2 theTimePeriod2 = new TimePeriod2(start, end);
            OrganicUnit organicUnit;

            final Iterable<OrganicUnit> organicUnits = this.theController.allOrganicUnits();
            final SelectWidget<OrganicUnit> selector3 = new SelectWidget<OrganicUnit>("Organic Units:", organicUnits, new OrganicUnitPrinter());
            do {
                selector3.show();
                if (selector3.selectedOption() == 0) {
                    throw new InterruptedException();
                }
                organicUnit = selector3.selectedElement();
                if (organicUnit == null) {
                    System.out.printf("That is not a valid option.\n");
                }
            } while (organicUnit == null);

            Set<Meal> meals = new HashSet<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            final Iterable<MealType.MealTypes> mealTypes = this.theController.allMealTypes();
            final SelectWidget<MealType.MealTypes> selector = new SelectWidget<>("Meal types:", mealTypes, new MealTypePrinter());
            Calendar date = Calendar.getInstance();
            date.setTime(start.getTime());
            
            main_loop:
            do {

                Dish theDish = null;
                MealType theMealType = null;

                do {
                    System.out.printf("Registering meals for day %s.\nSelect EXIT to proceed to next day.", sdf.format(date.getTime()));
                    selector.show();
                    if (selector.selectedOption() == 0) {
                        date.add(Calendar.DAY_OF_MONTH, 1);
                        if (date.after(end)) {
                            break main_loop;
                        }
                        continue;
                    }
                    theMealType = new MealType(selector.selectedElement());
                    if (theMealType == null) {
                        System.out.printf("That is not a valid option.\n");
                    }
                } while (theMealType == null);

                do {
                    final Iterable<Dish> dishes = this.theController.allDishes();
                    final SelectWidget<Dish> selector2 = new SelectWidget<>("Dishes:", dishes, new DishPrinter());
                    selector2.show();
                    if (selector2.selectedOption() == 0) {
                        throw new InterruptedException();
                    }
                    theDish = selector2.selectedElement();
                    if (theDish == null) {
                        System.out.printf("That is not a valid option.\n");
                    }
                } while (theDish == null);

                meals.add(new Meal(theDish, theMealType, date));
            } while (true);

            if (meals.isEmpty()) {
                System.out.printf("You have made an empty Menu. It will be ignored...\n");
                return false;
            }

            try {
                this.theController.registerMenu(meals, organicUnit, theTimePeriod2);
                System.out.printf("Menu registered successfully.\n");
                return true;
            } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                System.out.println("You tried to enter a dish which already exists in the database.");
            }
        } catch (InterruptedException e) {
            System.out.printf("Going back to menu.\n");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
