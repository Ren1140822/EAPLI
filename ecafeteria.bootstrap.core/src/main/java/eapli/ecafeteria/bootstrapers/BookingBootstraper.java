package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.booking.CreateBookingController;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.util.DateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * @author Meireles
 */
public class BookingBootstraper implements Action {

    @Override
    public boolean execute() {
        CafeteriaUserService users = new CafeteriaUserService();
        final CafeteriaUser user1 = users.findCafeteriaUserByMecNumber("150330");
        final CafeteriaUser user2 = users.findCafeteriaUserByMecNumber("150331");

        final Calendar yesterday = DateTime.yesterday();
        final Calendar today = DateTime.now();
        final Calendar tomorrow = DateTime.tomorrow();

        final MealType lunch = new MealType(MealType.MealTypes.LUNCH);
        final MealType dinner = new MealType(MealType.MealTypes.DINNER);
        
        final MealRepository meals = PersistenceContext.repositories().meals();

        Iterator<Meal> list = meals.findByDateAndMealType(yesterday, dinner).iterator();
        final Meal yesterdayDinnerA = list.next();
        final Meal yesterdayDinnerB = list.next();

        list = meals.findByDateAndMealType(today, lunch).iterator();
        final Meal todayLunchA = list.next();
        final Meal todayLunchB = list.next();

        list = meals.findByDateAndMealType(today, dinner).iterator();
        final Meal todayDinnerA = list.next();
        final Meal todayDinnerB = list.next();

        list = meals.findByDateAndMealType(tomorrow, lunch).iterator();
        final Meal tomorrowLunchA = list.next();
        final Meal tomorrowLunchB = list.next();

        register(user1, yesterdayDinnerA);
        register(user1, todayLunchB);
        register(user1, todayDinnerA);
        register(user1, tomorrowLunchB);

        register(user2, yesterdayDinnerB);
        register(user2, todayLunchA);
        register(user2, todayDinnerB);
        register(user2, tomorrowLunchA);

        return false;
    }

    /**
     * It registers a booking at a certain state.
     */
    private void register(CafeteriaUser user, Meal meal) {
        final CreateBookingController theBookingController = new CreateBookingController();
        try {
            theBookingController.registerBooking(user, meal);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-BO001: bootstrapping existing record");
        }
    }

}
