package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

import java.util.*;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class RegisterDishUI extends AbstractUI {

    private final RegisterDishController theController = new RegisterDishController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.dishTypes();

        final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();

        final String name = Console.readLine("Name");

        final NutricionalInfoDataWidget nutricionalData = new NutricionalInfoDataWidget();

        nutricionalData.show();

        final double price = Console.readDouble("Price");

        Dish createdDish = null;
        try {
            createdDish = this.theController.registerDish(theDishType, name, nutricionalData.calories(), nutricionalData.salt(),
                    price);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        } catch (IllegalStateException i) {
            boolean invalid = true;
            System.out.println("You tried to enter invalid information for the dish."
                    + "\nReenter info?"
                    + "\n1.Yes"
                    + "\n2.Leave\n");
            while (invalid) {
                final double opt = Console.readDouble("Option: ");
                switch ((int) opt) {
                    case 1:
                        show(); //rerun method
                        break;
                    case 2:
                        return false; //leaves UI
                    default:
                        System.out.println("The only options are:\n1.Yes\n2.Leave\n(Choose '1' or '2')\n");
                        break;
                }
            }
        }

        boolean hasAllergens = askForAllergens();

        if (hasAllergens) {
            boolean stop = false;
            List<Allergen> allergens = (List<Allergen>) theController.getAllergens();
            List<Allergen> allergensToAdd = new ArrayList<>();
            ListAllergenUI listAllergens = new ListAllergenUI();
            System.out.println("Select allergens contained in the new dish\n");
            listAllergens.show();
            System.out.println("Type the index of the first allergen to add (-1 to cancel)\n");
            int numAllergens = allergens.size();
            do {
                Scanner in = new Scanner(System.in);
                int index=-1;
                try {
                    index = in.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input\n");
                    System.out.println("Insert valid index, or -1 to cancel\n");
                    continue;
                }
                if (index == -1) {
                    stop=true;

                } else if (index > numAllergens || index<0){
                    System.err.println("Invalid index\n");
                } else {
                    allergensToAdd.add(allergens.get(index));
                }
                System.out.println("Insert another index to add or type -1 to finish\n");
            } while (!stop);
            if (!allergensToAdd.isEmpty()) {
                try {
                    theController.addAllergensToDish(allergensToAdd, createdDish);
                    System.out.printf("Dish was saved successfully with %d allergens.\n", allergensToAdd.size());
                } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                        System.out.println("You tried to enter a dish which already exists in the database.");
                    }
            }else{
                System.out.println("Dish was saved successfully without any allergen\n");
            }
        }

        return false;
    }

    private boolean askForAllergens() {
        Scanner in = new Scanner(System.in);
        System.out.println("Does this dish have allergens? (Y/N)\n");
        do {
            String op = in.next();
            switch (op) {
                case "y":
                case "Y":
                    return true;
                case "n":
                case "N":
                    return false;
                default:
                    System.out.println("Invalid Input, answer with 'Y' for yes or 'N' for no\n");
                    break;
            }

        } while (true);
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
