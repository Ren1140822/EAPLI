/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long>
        implements BookingRepository {

    @Override
    public Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state) {
        return match("e.user=:" + user + " and e.state=:" + state);
    }

}
