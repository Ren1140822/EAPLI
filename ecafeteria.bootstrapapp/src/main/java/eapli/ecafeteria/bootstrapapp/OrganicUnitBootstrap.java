package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.cafeteria.AddOrganicUnitController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author arocha
 */
public class OrganicUnitBootstrap implements Action {

    @Override
    public boolean execute() {
        register("ISEP", "Instituto Superior de Engenharia do PORTO", "Good school :)");
        register("HSJ", "Hospital São João", "An hospital...");

        return false;
    }

    /**
     *
     */
    private void register(String acronym, String name, String description) {
        final AddOrganicUnitController controller = new AddOrganicUnitController();
        try {
            controller.addOrganicUnit(acronym, name, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated organic unit
        }
    }
}