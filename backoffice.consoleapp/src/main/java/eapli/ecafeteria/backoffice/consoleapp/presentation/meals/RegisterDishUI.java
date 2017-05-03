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
import org.eclipse.persistence.internal.oxm.schema.model.All;

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
        }

        boolean hasAllergens = askForAllergens();

        if(hasAllergens){
            boolean stop = false;
            Collection<Allergen> allergens = (Collection<Allergen>) this.theController.allergens();
            Set<Allergen> allergensToAdd = new HashSet<>();
            ListAllergenUI listAllergens = new ListAllergenUI();
            System.out.println("Select allergens contained in the new dish\n");
            listAllergens.show();
            System.out.println("Type the index of the first allergen to add (-1 to cancel)\n");
            int numAllergens = allergens.size();
            Scanner in = new Scanner(System.in);
            do {
                int index;
                try {
                   index  = in.nextInt();
                }catch(InputMismatchException e){
                    System.err.println("Invalid input\n");
                    break;
                }
                if (index == -1) {
                    break;
                } else if (index > numAllergens){
                    System.err.println("Invalid index\n");
                }else{
                    int i=0;
                    Allergen elem = null;
                    while (i < index) {
                        elem = allergens.iterator().next();
                        i++;
                    }
                    allergensToAdd.add(elem);
                }
                System.out.println("Insert another index to add or type -1 to finish\n");
            }while(!stop);
            if(!allergensToAdd.isEmpty()) {
                theController.addAllergensToDish(allergensToAdd, createdDish);
                System.out.println("Allergens added to dish\n");
            }
        }
        return false;
    }



    private boolean askForAllergens(){
        Scanner in = new Scanner(System.in);
        System.out.println("Does this dish have allergens? (Y/N)\n");
        do {
            String op = in.next();
            switch(op){
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

        }while(true);
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
