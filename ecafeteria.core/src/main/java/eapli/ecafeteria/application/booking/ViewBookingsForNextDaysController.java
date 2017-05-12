/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.framework.application.Controller;

/**
 * The controller to view the active bookings for the next user-defined number
 * of days.
 *
 * @author Meireles
 */
public class ViewBookingsForNextDaysController implements Controller {

    private final CafeteriaUserService usersService = new CafeteriaUserService();
    private final ListBookingsService bookingsService = new ListBookingsService();

    /**
     * It provides all bookings done and definitive of the current authenticated
     * user whose meal occurs within the next specified number of days.
     *
     * @param days The number of days within the booking's meal should occur.
     * @return It returns all matching bookings.
     */
    public Iterable<Booking> listBookingsOfNext(int days) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        CafeteriaUser user = usersService.findCafeteriaUserByUsername(Application.session().session().authenticatedUser().username());
        return bookingsService.findActiveBookingsFromNextDaysOf(user, days);
    }

}
