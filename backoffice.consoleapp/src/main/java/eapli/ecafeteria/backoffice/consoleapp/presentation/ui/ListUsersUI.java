/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import java.util.ArrayList;
import java.util.List;

import eapli.cafeteria.consoleapp.presentation.visitors.UserUIVisitor;
import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.application.ListUsersController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.util.AddRoleType2List;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.framework.actions.ReturnAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.util.Console;
/**
 *
 * @author losa
 */
public class ListUsersUI extends AbstractUI {
    private final ListUsersController theController = new ListUsersController();
    
     @Override
    protected Controller controller() {
        return theController;
    }
    
        protected boolean doShow() {
        Iterable<User> iterable = theController.listUsers();
//        return doShowIterable(iterable);
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
           // System.out.printf("%30s\n", "List of Users ---");
            for (User user : iterable) {
                //FIX display other attributes
                System.out.printf("%30s %30s\n",user.name().firstName(),user.name().lastName());
            }
        }
        return true;
    }

    protected boolean doShowIterable(Iterable<User> iterable) {
        int i;
        
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered Dish Type");
        } else {
            System.out.printf("%-6s%-30s%6s\n", "Key", "Dish Type description", "Active");
            i = 0;
            for (User dT : iterable) {
                i++;
                //FIX
               // System.out.printf("%-6d%-30s%6s\n", i, dT.description(), dT.isActive());
            }
        }
        return true;
    }


    
    
    @Override
    public String headline() {
        return "List Users";
    }
}
