package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cashregister.OpenCashRegisterController;
import eapli.ecafeteria.application.delivery.RegisterMealDeliveryController;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegister;
import eapli.ecafeteria.domain.cafeteria.cashregister.CashRegisterId;
import eapli.ecafeteria.domain.cafeteria.cashregister.Shift;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.CashRegisterRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ShiftRepository;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * @author Eric Amaral - 1141570@isep.ipp.pt
 * @author Tiago Correia - 1151031@isep.ipp.pt
 */
public class ShiftBootstraper implements Action {

    @Override
    public boolean execute() {
        String mecanographicNumber1 = "150330";
        String mecanographicNumber2 = "150331";

        CashRegisterId cashRegisterId = new CashRegisterId("10001");

        final Calendar yesterday = DateTime.yesterday();
        final Calendar today = DateTime.now();

        final MealType lunch = new MealType(MealType.MealTypes.LUNCH);
        final MealType dinner = new MealType(MealType.MealTypes.DINNER);

        openCashRegister(cashRegisterId, dinner, yesterday);
        registerMealDelivery(mecanographicNumber1);
        registerMealDelivery(mecanographicNumber2);
        closeCashRegister(cashRegisterId);
        closeShift(yesterday, dinner);

        openCashRegister(cashRegisterId, lunch, today);
        registerMealDelivery(mecanographicNumber1);
        registerMealDelivery(mecanographicNumber2);
        closeCashRegister(cashRegisterId);
        closeShift(today, lunch);

        return false;
    }

    private void openCashRegister(CashRegisterId cashRegisterId, MealType mealType, Calendar date) {
        OpenCashRegisterController controller = new OpenCashRegisterController();
        try {
            controller.openCashRegister(cashRegisterId, mealType, date);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }

    }

    private void registerMealDelivery(String mecanographicNumber) {
        RegisterMealDeliveryController controller = new RegisterMealDeliveryController(mecanographicNumber);
        controller.registerMealDelivery();
    }

    //FIXME
    //@author Meireles
    // Should it be replaced by the "Fechar Caixa" Use case?
    private void closeCashRegister(CashRegisterId cashRegisterId) {
        final CashRegisterRepository cashRegisterRepository = PersistenceContext.repositories().cashRegisters();
        CashRegister cashRegister = cashRegisterRepository.findByCashRegisterId(cashRegisterId);
        cashRegister.close();
        try {
            cashRegisterRepository.save(cashRegister);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

    //FIXME
    //@author Meireles
    // Should it be replaced by the "Fechar Caixa" Use case?
    private void closeShift(Calendar date, MealType mealType) {
        final ShiftRepository shifts = PersistenceContext.repositories().shifts();
        try {
            Shift shift = shifts.findByDateAndMealType(date, mealType);
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
