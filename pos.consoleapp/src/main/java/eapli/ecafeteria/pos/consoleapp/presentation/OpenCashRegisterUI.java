package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.cashregister.OpenCashRegisterController;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class OpenCashRegisterUI extends AbstractUI {

    public static final int YEAR = 0;
    public static final int MONTH = 1;
    public static final int DAY = 2;
    public static final int LUNCH = 1;

    private final OpenCashRegisterController theController = new OpenCashRegisterController();

    @Override
    protected boolean doShow() {

        final String stringCashRegisterId = Console.readLine("Insert the Cash Register id: ");
        CashRegisterId cashRegisterId = new CashRegisterId(stringCashRegisterId);

        final String stringDate = Console.readLine("Insert the Date (YYYY-MM-DD): ");
        String[] date = stringDate.split("-");
        //Month value in Calendar is 0 based -> January = 00, February = 01 (...) so the user's month value input must be decremented by one
        Calendar inputDate = new GregorianCalendar(
                Integer.parseInt(date[YEAR]),
                ((Integer.parseInt(date[MONTH])) - 1),
                Integer.parseInt(date[DAY])
        );

        final Integer mealType = Console.readInteger("Select the Meal Type:\n1 - Lunch\n2 - Dinner\n");
        MealType chosenMealType;
        if (mealType == LUNCH) {
            chosenMealType = new MealType(MealType.MealTypes.ALMOCO);
        } else {
            chosenMealType = new MealType(MealType.MealTypes.JANTAR);
        }

        boolean success = false;
        try {
            this.theController.openCashRegister(cashRegisterId, chosenMealType, inputDate);

            success = !success; // Turn to success to true

        } catch (DataConcurrencyException ex) {
            System.out.println("That entity has already been changed or deleted since you last read it");
            Logger.getLogger(OpenCashRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            System.out.println("That entity ID is already in use");
            Logger.getLogger(OpenCashRegisterUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (success) {
            // Success message
            System.out.println("Cash Register was successfully opened.");
        } else {
            // Unuccess message
            System.out.println("Cash Register opening was unsuccessfull.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Cash Register Opening";
    }

}
