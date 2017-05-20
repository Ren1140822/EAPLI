/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.application.cafeteria.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.account.AccountCard;
import eapli.ecafeteria.domain.cafeteria.account.Balance;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;

/**
 *
 * @author mcn
 */
public class CafeteriaUserBaseController implements Controller {

    private final CafeteriaUserService usersService = new CafeteriaUserService();
    private final ListBookingsService bookingsService = new ListBookingsService();
    private final AccountCardRepository accountCardRepository = PersistenceContext.repositories().accountCards(null);

    /**
     * It provides the current user's id.
     * 
     * @return It returns the username of the current user.
     */
    public Username id(){
        return Application.session().session().authenticatedUser().username();
    }

    /**
     * It provides the current balance of the current system user.
     *
     * @return It returns the amount that the current system user has available
     * in his account.
     */
    public Money balance() {
        Username username = Application.session().session().authenticatedUser().username();
        CafeteriaUser client = usersService.findCafeteriaUserByUsername(username);
        AccountCard accountCard = accountCardRepository.findByMecanographicNumber(client.mecanographicNumber());
        Balance balance = accountCard.balance();
        return balance.amount();
    }

    /**
     * It provides the next active booking to the current system user.
     *
     * @return It returns the next active booking that the current user made.
     */
    public Booking nextBooking() {
        Username username = Application.session().session().authenticatedUser().username();
        CafeteriaUser client = usersService.findCafeteriaUserByUsername(username);
        return bookingsService.findNextActiveBookingOf(client);
    }
}
