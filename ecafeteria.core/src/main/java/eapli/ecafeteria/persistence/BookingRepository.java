/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;


import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author nunopinto
 */
public interface BookingRepository extends DataRepository<Booking, Long> {
    
    Booking findByMecanographicNumber(MecanographicNumber number);
    
    Booking findByMeal(Meal meal);
    
}
