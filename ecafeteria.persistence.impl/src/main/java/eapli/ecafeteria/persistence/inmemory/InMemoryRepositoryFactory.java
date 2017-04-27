package eapli.ecafeteria.persistence.inmemory;

//import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstraper;
import eapli.ecafeteria.persistence.*;

/**
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new ECafeteriaBootstraper().execute();
    }

    @Override
    public UserRepository users(boolean tx) {
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
    public CafeteriaUserRepository cafeteriaUsers(boolean tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(boolean tx) {
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
    public AccountCardRepository accountCards() {
        return new InMemoryAccountCardRepository();
    }

    @Override
    public TransactionRepository transactions() {
        return new InMemoryTransactionRepository();
    }

    @Override
    public AllergenRepository allergens() { return new InMemoryAllergenRepository(); }
}
