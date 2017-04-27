/**
 *
 */
package eapli.ecafeteria.persistence;

/**
 * @author Paulo Gandra Sousa
 */
public interface RepositoryFactory {

    /**
     * @param autoTx declares if the repository should be created in auto
     *               transaction mode or if the caller will take care of transactions
     * @return
     */
    UserRepository users(boolean autoTx);

    DishTypeRepository dishTypes();

    OrganicUnitRepository organicUnits();

    /**
     * @param autoTx declares if the repository should be created in auto
     *               transaction mode or if the caller will take care of transactions
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(boolean autoTx);

    /**
     * @param autoTx declares if the repository should be created in auto
     *               transaction mode or if the caller will take care of transactions
     * @return
     */
    SignupRequestRepository signupRequests(boolean autoTx);

    DishRepository dishes();

    MaterialRepository materials();

    MenuRepository menus();

    MealTypeRepository mealTypes();

    AccountCardRepository accountCards();

    TransactionRepository transactions();
    
    BookingRepository bookings();
}
