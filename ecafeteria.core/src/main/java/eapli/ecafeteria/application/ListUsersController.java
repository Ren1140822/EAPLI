/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.users.SystemUser;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;

/**
 *
 * @author losa
 */
public class ListUsersController implements Controller {

    public Iterable<SystemUser> listUsers(){
        UserRepository userRepository = PersistenceContext.repositories().users();
        return userRepository.all();
    }

    
}