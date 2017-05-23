package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * an application service to avoid code duplication.
 */
class ListDishTypeService {

    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    public Iterable<DishType> allDishTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.findAll();
    }

    public Iterable<DishType> allDishTypesMANAGER() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.dishTypeRepository.findAll();
    }

    public Iterable<DishType> activeDishTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.activeDishTypes();
    }

    /**
     * Retrieves a map with the available dish types as key and the occurrences
     * as value.
     *
     * @return available dish types
     */
    public Map<DishType, Integer> availableDishTypes() {
        Map<DishType, Integer> availableDishTypes = new HashMap<>();

        // Add all dish types to the available dish types with 0 occurrences
        Iterable<DishType> dishTypes = allDishTypes();
        for (DishType aDishType : dishTypes) {
            availableDishTypes.put(aDishType, 0);
        }

        // Retrieve the open shift
        ShiftRepository aShiftRepository = PersistenceContext.repositories().shifts();
        ShiftState openState = ShiftState.OPENED;
        Iterator<Shift> shifts = aShiftRepository.findByState(openState).iterator();
        if (!shifts.hasNext()) {
            // if there is no shift opened, then the
            // available meals for each dish type is zero.
            return availableDishTypes;
        }
        Shift openShift = shifts.next();

        // Add the prepared meals to the available dish types
        MealsPreparedRepository mpr = PersistenceContext.repositories().mealsPrepared();
        Iterable<MealsPrepared> mpl = mpr.findByShift(openShift);
        for (MealsPrepared mp : mpl) {
            DishType type = mp.dishType();
            Integer dishTypeQuantity = mp.quantity();

            Integer count = availableDishTypes.get(type);
            availableDishTypes.put(type, count + dishTypeQuantity);
        }

        // Retrieve delivered meals by dish type and subtract to the available dish types
        for (DishType aDishType : dishTypes) {
            BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);
            // Integer quantity = bookingRepository.deliveredMeals(aDishType, openShift);
            Integer quantity = 0; // TODO get the quantity

            Integer count = availableDishTypes.get(aDishType);
            availableDishTypes.put(aDishType, count - quantity);
        }

        return availableDishTypes;
    }
}