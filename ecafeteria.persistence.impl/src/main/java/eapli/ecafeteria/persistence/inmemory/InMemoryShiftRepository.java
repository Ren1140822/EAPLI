package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class InMemoryShiftRepository extends InMemoryRepositoryWithLongPK<Shift>
        implements ShiftRepository {

    @Override
    public Shift findByDateAndMealType(Calendar date, MealType mealType) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
