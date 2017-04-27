package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.*;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(boolean autoTx) {
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
    public JpaCafeteriaUserRepository cafeteriaUsers(boolean autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests(boolean autoTx) {
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
    public MealTypeRepository mealTypes() {
        return new JpaMealTypeRepository();
    }


}
