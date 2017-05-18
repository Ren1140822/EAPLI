/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.cashregister.RegisterComplaintController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.DishPrinter;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 * @author Diogo Santos [1150451@isep.ipp.pt]
 */
public class RegisterComplaintUI extends AbstractUI {

    private final RegisterComplaintController controller = new RegisterComplaintController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        String complaint = Console.readLine("Write your complaint:");
        this.controller.insertComplaint(complaint);

        int optDish = Console.readInteger("Do you want to insert a dish on complaint?\n1.Yes\n2.No");
        int optNumber = 0;
        Dish selectedDish;

        if (optDish == 1) {
            final Iterable<Dish> dishes = this.controller.listDishes();
            if (!dishes.iterator().hasNext()) {
                System.out.println("There are no registered dishes!");
            } else {
                do {
                final SelectWidget<Dish> selector = new SelectWidget<>("Dishes:", dishes, new DishPrinter());
                selector.show();
                selectedDish = selector.selectedElement();
                } while (selectedDish==null);
                this.controller.insertDish(selectedDish);
            }
        }

        optNumber = Console.readInteger("Do you want to insert your mecanographic mumber on complaint?\n1.Yes\n2.No");
        if (optNumber == 1) {
            boolean res = false;
            do {
                int number = Console.readInteger("Insert your number:");

                res = this.controller.insertMecanograficNumber(number);
            } while (!res);
        }

        try {
            this.controller.saveComplaint();
            System.out.println("Complaint Successful Registered!");
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterComplaintUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Complaint";
    }
}
