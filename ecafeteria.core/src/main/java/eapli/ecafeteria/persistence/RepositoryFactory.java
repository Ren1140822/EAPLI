/**
 *
 */
package eapli.ecafeteria.persistence;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    UserRepository users();

    DishTypeRepository dishTypes();

    OrganicUnitRepository organicUnits();

    CafeteriaUserRepository cafeteriaUsers();

    SignupRequestRepository signupRequests();

    DishRepository dishes();

    MaterialRepository materials();
}
