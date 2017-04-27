package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nunopinto
 */
public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking,Long> implements BookingRepository {
    
    @Override
    public Iterable<Booking> findByMecanographicNumber(MecanographicNumber number) {
       return match("e.user.mecanographicNumber:="+number);
    }

    @Override
    public Iterable<Booking> findByMeal(Meal meal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
