package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.logging.Logger;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public class AllergenBootstraper implements Action {
    @Override
    public boolean execute() {
        /*/AllergenRepository repo = PersistenceContext.repositories().allergens();
        register()/*/
        return true;
    }


    private void register(Designation name, String description) {
        /*/final RegisterAllergenController controller = new RegisterAllergenController();
        try {
            controller.registerAllergen(name, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }/*/
    }
}
