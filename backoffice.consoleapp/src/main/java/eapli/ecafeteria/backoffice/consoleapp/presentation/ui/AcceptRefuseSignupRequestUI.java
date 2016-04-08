/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.AcceptRefuseSignupRequestController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.ui.*;
import eapli.ecafeteria.application.ChangeDishTypeController;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Console;

/**
 *
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestUI extends AbstractUI {

    private final AcceptRefuseSignupRequestController theController = new AcceptRefuseSignupRequestController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<SignupRequest> signupRequests = this.theController.listSignupRequestsPending();
        final SelectWidget<SignupRequest> selector = new SelectWidget<>(signupRequests, new SignupRequestPrinter());
        selector.show();
        final SignupRequest theSignupRequest = selector.selectedElement();
        if (theSignupRequest != null) {
            //FIXME accept SignupRequest 
            this.theController.acceptSignupRequest(theSignupRequest);

            //FIXME refuse SignupRequest 
            this.theController.refuseSignupRequest(theSignupRequest);

        }
        return false;
    }

    @Override
    public String headline() {
        return "Accept of Refuse Signup Requests";
    }
}
