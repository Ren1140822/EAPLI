/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterDishController;
import eapli.ecafeteria.domain.meals.Allergen;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
public class DishBootstraper implements Action {

    @Override
    public boolean execute() {

        final DishTypeRepository dishTypeRepo = PersistenceContext.repositories().dishTypes();
        final AllergenRepository allergenRepository = PersistenceContext.repositories().allergens();
        final DishType vegie = dishTypeRepo.findByAcronym("vegie");
        final DishType fish = dishTypeRepo.findByAcronym("fish");
        final DishType meat = dishTypeRepo.findByAcronym("meat");
        final List<Allergen> allergens= (List<Allergen>) allergenRepository.allergens();
        final Allergen molluscs = allergens.get(13);
        final Allergen cereals = allergens.get(0);
        final List<Allergen>allergenSet1 = new ArrayList<>();
        final List<Allergen>allergenSet2 = new ArrayList<>();
        allergenSet1.add(cereals);
        allergenSet2.add(molluscs);
        allergenSet2.add(cereals);

        register(vegie, "tofu grelhado", 10, 1, 2.99, null);
        register(vegie, "lentilhas salteadas", 10, 1, 2.85,allergenSet1);
        register(fish, "bacalhau à braz", 50, 2, 3.99,allergenSet1);
        register(fish, "lagosta suada", 50, 2, 24.99,null);
        register(meat, "picanha", 75, 2, 4.99,null);
        register(meat, "costeleta à salsicheiro", 75, 2, 3.99,null);
        register(fish, "camarões grelhados", 50, 10, 10.00, allergenSet2);
        return false;
    }

    /**
     *
     */
    private void register(DishType dishType, String description, int cal, int salt, double price, List<Allergen>allergens) {
        final RegisterDishController controller = new RegisterDishController();
        try {
            Dish dish;
            dish = controller.registerDish(dishType, description, cal, salt, price);
            if(allergens!=null){
                controller.addAllergensToDish(allergens,dish);
            }
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
