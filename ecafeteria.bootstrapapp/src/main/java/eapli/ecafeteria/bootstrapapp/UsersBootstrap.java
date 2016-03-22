/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import eapli.ecafeteria.application.UserRegisterController;
import eapli.ecafeteria.domain.users.Password;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.framework.actions.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pgsou_000
 */
public class UsersBootstrap implements Action {

    public boolean execute() {

        //Username username = new Username("admin");
        //Password password = new Password("admin");

        String username = new String("admin");
        String password = new String("admin");

        String firstName = new String("John");
        String lastName = new String("Doe");

        String email = new String("john.doe@emai.l.com");

        List<RoleType> roles = new ArrayList<>();

        UserRegisterController userController = new UserRegisterController();
        User newUser = userController.registerUser(username, password, firstName, lastName, email, roles);
        return false;

    }
}
