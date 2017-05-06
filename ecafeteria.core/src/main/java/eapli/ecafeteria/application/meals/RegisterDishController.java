package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class RegisterDishController implements Controller {

    private ListDishTypeService svcDishTypes = new ListDishTypeService();

    private DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Dish registerDish(final DishType dishType, final String name, final Integer calories, final Integer salt,
            final double price) throws DataIntegrityViolationException, DataConcurrencyException {

        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Dish newDish = new Dish(dishType, Designation.valueOf(name), new NutricionalInfo(calories, salt),
                Money.euros(price));

        Dish ret = this.dishRepository.save(newDish);

        return ret;
    }

    private ListAllergensController ctrlAllergens;
    
    public Iterable<Allergen> getAllergens(){
        ctrlAllergens = new ListAllergensController();
        return ctrlAllergens.allergens();
    }

    private RegisterDishAllergenController ctrlRegDishAllergen;
    public void addAllergensToDish(final List<Allergen> allergens, Dish dish) throws DataConcurrencyException, DataIntegrityViolationException {
        ctrlRegDishAllergen = new RegisterDishAllergenController();
        AllergenicInfo allergenicInfo =  ctrlRegDishAllergen.registerDishAllergens(allergens);
        dish.addAllergenicInfo(allergenicInfo);
        this.dishRepository.save(dish);
    }

    public Iterable<DishType> dishTypes() {
        return this.svcDishTypes.activeDishTypes();
    }
}
