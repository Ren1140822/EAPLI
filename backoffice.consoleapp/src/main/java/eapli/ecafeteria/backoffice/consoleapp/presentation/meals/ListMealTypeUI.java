package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ListDishTypeController;
import eapli.ecafeteria.application.meals.ListMealTypeService;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ListMealTypeUI extends AbstractListUI<MealType.MealTypes> {

    private final ListMealTypeService service = new ListMealTypeService();

    @Override
    protected Iterable<MealType.MealTypes> listOfElements() {
        return this.service.allMealTypes();
    }

    @Override
    protected Visitor<MealType.MealTypes> elementPrinter() {
        return new MealTypePrinter();
    }

    @Override
    protected String elementName() {
        return "Dish Type";
    }
}
