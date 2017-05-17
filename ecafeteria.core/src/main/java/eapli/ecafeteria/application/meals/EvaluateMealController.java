/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Comment;
import eapli.ecafeteria.domain.meals.MealEvaluation;
import eapli.ecafeteria.domain.meals.Rating;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealEvaluationRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * The controller to select and evaluate a booked delivered of the cafeteria
 * user.
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
public class EvaluateMealController implements Controller {

    /**
     * The Repository of Meal Evaluation.
     */
    private final MealEvaluationRepository mealEvaluationRepository = PersistenceContext.repositories().mealEvaluations();

    /**
     * The Repository of Booking.
     */
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings(null);

    /**
     * The Cafeteria User Service.
     */
    private final CafeteriaUserService usersService = new CafeteriaUserService();

    /**
     * It gets an iterable list with all the delivered bookings to be evaluated.
     *
     * @return It returns an iterable list with the result.
     */
    public Iterable<Booking> listDeliveredBookings() {
        Username username = Application.session().session().authenticatedUser().username();
        CafeteriaUser user = usersService.findCafeteriaUserByUsername(username);
        return bookingRepository.allNonEvaluatedBy(user, BookingState.DELIVERED);
    }

    /**
     * It creates a meal evaluation and saves it to the meal evaluation
     * repository.
     *
     * @param booking The booking chosed to be evaluated.
     * @param rating The Rating (1-5) of the evaluation.
     * @param comment The string commentary of the evaluation, that can be
     * empty.
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void mealEvaluation(Booking booking, Rating rating, Comment comment) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        final MealEvaluation mealEvaluation = new MealEvaluation(booking, rating, comment);
        MealEvaluation ret = mealEvaluationRepository.save(mealEvaluation);
    }

}
