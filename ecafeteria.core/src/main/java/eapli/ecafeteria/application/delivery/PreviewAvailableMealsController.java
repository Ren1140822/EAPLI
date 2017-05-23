package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.cafeteria.cashregister.ShiftState;
import eapli.ecafeteria.domain.kitchen.MealsPrepared;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The controller to preview available meals per each dish type.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class PreviewAvailableMealsController implements Controller {

    /**
     * Retrieves a map with the available dish types as key and the occurrences
     * as value.
     *
     * @return available dish types
     */
    public Map<DishType, Integer> availableDishTypes() {
        Map<DishType, Integer> availableDishTypes = new HashMap<>();

        // Add all dish types to the available dish types with 0 occurrences
        DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        Iterable<DishType> dishTypes = dishTypeRepository.findAll();
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

        // Add the prepared meals for each dish types
        MealsPreparedRepository mpr = PersistenceContext.repositories().mealsPrepared();
        Iterable<MealsPrepared> mpl = mpr.findByShift(openShift);
        for (MealsPrepared mp : mpl) {
            DishType type = mp.dishType();
            Integer dishTypeQuantity = mp.quantity();

            Integer count = availableDishTypes.get(type);
            availableDishTypes.put(type, count + dishTypeQuantity);
        }

        // Retrieve delivered meals by dish type and subtract to the available meals of each dish types
        BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);
        for (DishType aDishType : dishTypes) {

            Integer quantity = bookingRepository.countDeliveredMeals(openShift, aDishType).intValue();

            Integer count = availableDishTypes.get(aDishType);
            availableDishTypes.put(aDishType, count - quantity);
        }

        return availableDishTypes;
    }
}
