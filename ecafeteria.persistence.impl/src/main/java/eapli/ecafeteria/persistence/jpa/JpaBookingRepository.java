/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long>
        implements BookingRepository {

    @Override
    public Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state) {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("state", state);
        return match("e.user=:user and e.state=:state", params);
    }

}
