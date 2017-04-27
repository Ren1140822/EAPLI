/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author PC
 */
public class BookingRegistrationController {
    
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings();
    
    
    
    
    
}
