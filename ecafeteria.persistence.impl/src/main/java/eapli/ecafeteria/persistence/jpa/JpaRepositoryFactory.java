package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaTransactionalContext;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public OrganicUnitRepository organicUnits() {
        return new JpaOrganicUnitRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public MenuRepository menus() {

        return new JpaMenuRepository();
    }

    @Override
    public AccountCardRepository accountCards() {
        return new JpaAccountCardRepository();
    }

    @Override
    public TransactionRepository transactions() {
        return new JpaTransactionRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    public BookingRepository bookings() {
        return new JpaBookingRepository();
    }

    @Override
    public AllergenRepository allergens() {
        return new JpaAllergenRepository();
    }

    @Override
    public MealsPreparedRepository mealsPrepared() {
        return new JpaMealsPreparedRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return new JpaTransactionalContext(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MealEvaluationRepository mealEvaluations() {
        return new JpaMealEvaluationRepository();
    }

    @Override
    public MaterialUsedRepository materialUsed() {
        return new JpaMaterialUsedRepository();
    }
}
