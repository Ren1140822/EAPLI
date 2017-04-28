package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.AddAllergensService;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.logging.Logger;

/**
 * Created by k4rd050 on 27-04-2017.
 */
public class AllergenBootstraper implements Action {
    @Override
    public boolean execute() {
        AllergenRepository repo = PersistenceContext.repositories().allergens();
        register("Cereals containing gluten","wheat, rye, barley, oats or their hybridised strains, and products thereof");
        register("Crustaceans and products thereof","");
        register("Eggs and products thereof","");
        register("Fish and products thereof","");
        register("Peanuts and products thereof","");
        register("Soybeans and products thereof","");
        register("Milk and products thereof","Milk and products thereof (including lactose)");
        register("Nuts","almonds, hazelnuts, walnuts, cashews, pecan nuts, Brazil nuts, pistachio nuts, macadamia, and products thereof, except for nuts used for making alcoholic distillates including ethyl alcohol of agricultural origin");
        register("Celery and products thereof","");
        register("Mustard and products thereof","");
        register("Sesame seeds and products thereof","");
        register("Sulphur dioxide and sulphites","Sulphur dioxide and sulphites at concentrations of more than 10 mg/kg or 10 mg/litre");
        register("Lupin and products thereof","");
        register("Molluscs and products thereof","");
        return false;
    }


    private void register(String name, String description) {
        final AddAllergensService svc = new AddAllergensService();
        try {
            svc.registerAllergen(name, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
