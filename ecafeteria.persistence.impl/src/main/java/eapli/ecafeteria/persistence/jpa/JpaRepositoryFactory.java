package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users() {
        return new JpaUserRepository();
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
    public JpaCafeteriaUserRepository cafeteriaUsers() {
        return new JpaCafeteriaUserRepository();
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository();
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }
}
