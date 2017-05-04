/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Comment;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.ecafeteria.domain.meals.Rating;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealEvaluationRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Pedro
 */
public class EvaluateMealController implements Controller {

    private final ListBookingsService svc = new ListBookingsService();
    private final MealEvaluationRepository mealEvaluationRepository = PersistenceContext.repositories().mealEvaluations();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers(null);

    public Iterable<Booking> listDeliveredBookings() {
        CafeteriaUser user = userRepository.findByUsername(Application.session().session().authenticatedUser().username());
        return svc.findBookingsStateDeliveredOf(user);
    }

    public void mealEvaluation(Booking booking, int rating, String comment) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        final MealEvaluation mealEvaluation = new MealEvaluation(booking, new Rating(rating), new Comment(comment));

        MealEvaluation ret = mealEvaluationRepository.save(mealEvaluation);
    }
}
