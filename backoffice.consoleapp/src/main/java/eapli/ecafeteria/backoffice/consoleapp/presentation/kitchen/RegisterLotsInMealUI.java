package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterLotsInMealController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva (1151088@isep.ipp.pt)
 */
public class RegisterLotsInMealUI extends AbstractUI {
 
     private final RegisterLotsInMealController theController= new RegisterLotsInMealController();

     protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {
      //TODO
        final String acronym_meal= Console.readLine(("Meal acronym: "));
        final String acronym_material=Console.readLine(("Material acronym: "));
        final String lot_code= Console.readLine(("Lot code: "));
        
        try{
            this.theController.selectMeal(Long.valueOf(acronym_meal));
            this.theController.selectMaterial(acronym_material);
            this.theController.fillMaterialUsed(lot_code);
            this.theController.saveRegistration();
            
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
             System.out.println("That material used is already in use.");
             
        }
        
        return false; 
    }

    @Override
    public String headline() {
        return "Register Lots in Meal";
    }
}
