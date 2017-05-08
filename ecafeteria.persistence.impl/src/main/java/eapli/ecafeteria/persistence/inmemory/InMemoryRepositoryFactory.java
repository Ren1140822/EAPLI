package eapli.ecafeteria.persistence.inmemory;

//import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new ECafeteriaBootstraper().execute();
    }

    @Override
    public UserRepository users(TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new InMemoryDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
        return new InMemoryOrganicUnitRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers(TransactionalContext tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public DishRepository dishes() {
        return new InMemoryDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new InMemoryMaterialRepository();
    }

    @Override
    public MenuRepository menus() {
        return new InMemoryMenuRepository();
    }

    @Override
    public AccountCardRepository accountCards(TransactionalContext autoTx) {
        return new InMemoryAccountCardRepository();
    }

    @Override
    public TransactionRepository transactions(TransactionalContext autoTx) {
        return new InMemoryTransactionRepository();
    }

    @Override
    public AllergenRepository allergens() {
        return new InMemoryAllergenRepository();
    }

    @Override
    public BookingRepository bookings(TransactionalContext tx) {
        return new InMemoryBookingRepository();
    }

    public MealRepository meals() {
        return new InMemoryMealRepository();
    }

    @Override
    public MealsPreparedRepository mealsPrepared() {
        return new InMemoryMealsPreparedRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public MealEvaluationRepository mealEvaluations() {
        return new InMemoryMealEvaluationRepository();
    }

    @Override
    public MaterialUsedRepository materialUsed() {
        return new InMemoryMaterialUsedRepository();
    }
    
    @Override
    public CashRegisterRepository cashRegisters() {
        return new InMemoryCashRegisterRepository();
    }
}
