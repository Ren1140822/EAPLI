package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cashregister.RegisterComplaintController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class ComplaintBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishes = PersistenceContext.repositories().dishes();
        final Dish dish = dishes.findByName(Designation.valueOf("Chop Sausage"));

        register("I did not like the dish!", dish, 150330);

        return false;
    }

    private void register(String complaint, Dish dish, int number) {
        final RegisterComplaintController controller = new RegisterComplaintController();
        try {
            controller.insertComplaint(complaint);
            controller.insertDish(dish);
            controller.insertMecanograficNumber(number);
            controller.saveComplaint();
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }

}
