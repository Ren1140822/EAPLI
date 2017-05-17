/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cashregister;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteria.cashregister.Complaint;
import eapli.ecafeteria.domain.cafeteria.cashregister.ComplaintBuilder;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * @author Diogo Santos
 */
public class RegisterComplaintController implements Controller {
   
    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers(TxCtx);
    private final ComplaintRepository complaintRepository = PersistenceContext.repositories().complaints();
    private ComplaintBuilder builder = new ComplaintBuilder();
    
    
    public RegisterComplaintController() {

    }

    public void insertComplaint(String text) {
        builder.withText(text);
    }

    public Iterable<Dish> listDishes(){
        return dishRepository.findAll();
    }
    public void insertDish(Dish dish) {
        builder.withDish(dish);
    }

    public void insertMecanograficNumber(int number) {
        CafeteriaUser user = userRepository.findByMecanographicNumber(new MecanographicNumber(String.valueOf(number)));
        if(user != null){
            builder.withMecanograficNumber(number);
        }  
    }
    
    public Complaint saveComplaint() throws DataConcurrencyException, DataIntegrityViolationException{
        //Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_DELIVERY);
        Complaint c = builder.build();
        return complaintRepository.save(c);
    }
    

}
