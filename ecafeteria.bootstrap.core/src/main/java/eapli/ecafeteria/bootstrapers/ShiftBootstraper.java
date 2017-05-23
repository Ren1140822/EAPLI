package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ShiftBootstraper implements Action {

    @Override
    public boolean execute() {
        final MealType mealType1 = new MealType(MealType.MealTypes.LUNCH);
        final MealType mealType2 = new MealType(MealType.MealTypes.DINNER);

        //Month value in Calendar is 0 based -> January = 00, February = 01 (...)
        final Calendar date1 = new GregorianCalendar(2017, 05, 10);
        final Calendar date2 = new GregorianCalendar(2017, 05, 11);

        registerClosedShift(date1, mealType1);
        registerClosedShift(date1, mealType2);
        register(date2, mealType1);

        return false;
    }

    private void register(Calendar date, MealType mealType) {
        final ShiftRepository shifts = PersistenceContext.repositories().shifts();
        try {
            shifts.save(new Shift(date, mealType));
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

    private void registerClosedShift(Calendar date, MealType mealType) {
        final ShiftRepository shifts = PersistenceContext.repositories().shifts();
        try {
            Shift shift = new Shift(date, mealType);
            shift.close();
            shifts.save(shift);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

}
