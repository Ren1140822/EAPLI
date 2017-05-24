package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.delivery.PreviewAvailableMealsController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;

import java.util.Map;

/**
 * Shows a vertical submenu with the preview of available meals.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class ShowVerticalSubMenuWithAvailableMeals extends ShowVerticalSubMenuAction {

    public ShowVerticalSubMenuWithAvailableMeals(Menu menu) {
        super(menu);
    }

    @Override
    public boolean execute() {
        displayAvailableMeals();
        return super.execute();
    }

    private void displayAvailableMeals() {
        PreviewAvailableMealsController previewAvailableMealsController = new PreviewAvailableMealsController();
        System.out.println();
        System.out.println("Available meals per dish type:");
        Map<DishType, Integer> availableDishTypes = previewAvailableMealsController.availableDishTypes();
        for (Map.Entry<DishType, Integer> entry : availableDishTypes.entrySet()) {
            System.out.printf("%10s -> %d available\n", entry.getKey().acronym(), entry.getValue());
        }
        System.out.println();
    }
}
